package model.dao;

import config.DatabaseConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.entity.EstacaoEntity;
import model.entity.LinhaEntity;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class TmpDAO {

    @Inject
    DatabaseConfig databaseConfig;

    public List<LinhaEntity> getLinhas() throws SQLException {
        List<LinhaEntity> linhas = new ArrayList<>();
        String query = "SELECT id_linha, nome, sigla, numero FROM linha WHERE numero IN (8, 9)";
        
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                LinhaEntity linha = new LinhaEntity(
                        resultSet.getInt("id_linha"),
                        resultSet.getString("nome"),
                        resultSet.getString("sigla"),
                        resultSet.getInt("numero")
                );
                linhas.add(linha);
            }
        }
        
        return linhas;
    }

    public List<EstacaoEntity> getEstacoesByLinha(int linhaId) throws SQLException {
        List<EstacaoEntity> estacoes = new ArrayList<>();
        String query = "SELECT e.id_estacao, e.nome, e.sigla, e.endereco " +
                "FROM estacao e " +
                "JOIN linha_estacao le ON e.id_estacao = le.id_estacao " +
                "WHERE le.id_linha = ?";
        
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, linhaId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    EstacaoEntity estacao = new EstacaoEntity(
                            resultSet.getInt("id_estacao"),
                            resultSet.getString("nome"),
                            resultSet.getString("sigla"),
                            resultSet.getString("endereco")
                    );
                    estacoes.add(estacao);
                }
            }
        }
        
        return estacoes;
    }    public Map<String, Object> calcularTempo(int linhaId, List<Integer> estacaoIds, LocalDate data) throws SQLException {
        System.out.println("Debug - Parameters: linhaId=" + linhaId + ", estacaoIds=" + estacaoIds + ", data=" + data);
        
        Map<String, Object> resultado = new HashMap<>();
        
        if (estacaoIds.size() != 2) {
            System.out.println("Debug - Expecting exactly 2 stations, got: " + estacaoIds.size());
            return resultado;
        }
        
        int estacaoPartida = estacaoIds.get(0);
        int estacaoDestino = estacaoIds.get(1);
        
        // Listar todas as viagens para debug
        String queryListarViagens = 
                "SELECT id_viagem, id_linha, id_estacao_partida, id_estacao_destino, data_partida, data_chegada " + 
                "FROM viagem WHERE id_linha = ?";
        
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryListarViagens)) {
            
            statement.setInt(1, linhaId);
            
            System.out.println("Debug - Verificando viagens existentes para a linha " + linhaId);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean encontrouViagem = false;
                while (resultSet.next()) {
                    encontrouViagem = true;
                    System.out.println("Viagem encontrada: id=" + resultSet.getInt("id_viagem") + 
                                      ", linha=" + resultSet.getInt("id_linha") + 
                                      ", partida=" + resultSet.getInt("id_estacao_partida") + 
                                      ", destino=" + resultSet.getInt("id_estacao_destino") + 
                                      ", data_partida=" + resultSet.getTimestamp("data_partida") + 
                                      ", data_chegada=" + resultSet.getTimestamp("data_chegada"));
                }
                if (!encontrouViagem) {
                    System.out.println("Nenhuma viagem encontrada para a linha " + linhaId);
                }
            }
        }
        
        // Calcular tempo médio entre estações - usando TO_CHAR para Oracle
        String queryTempoMedio = 
                "SELECT AVG(EXTRACT(MINUTE FROM (v.data_chegada - v.data_partida)) * 60 + " +
                "EXTRACT(SECOND FROM (v.data_chegada - v.data_partida))) as tempo_medio " +
                "FROM viagem v " +
                "WHERE v.id_linha = ? AND ((v.id_estacao_partida = ? AND v.id_estacao_destino = ?) OR " +
                "(v.id_estacao_partida = ? AND v.id_estacao_destino = ?)) " +
                "AND TO_CHAR(v.data_partida, 'YYYY-MM-DD') = ?";
        
        // Calcular tempo total
        String queryTempoTotal = 
                "SELECT SUM(EXTRACT(MINUTE FROM (v.data_chegada - v.data_partida)) * 60 + " +
                "EXTRACT(SECOND FROM (v.data_chegada - v.data_partida))) as tempo_total " +
                "FROM viagem v " +
                "WHERE v.id_linha = ? AND ((v.id_estacao_partida = ? AND v.id_estacao_destino = ?) OR " +
                "(v.id_estacao_partida = ? AND v.id_estacao_destino = ?)) " +
                "AND TO_CHAR(v.data_partida, 'YYYY-MM-DD') = ?";
        
        // Calcular diferença do último mês
        LocalDate dataUltimoMes = data.minusMonths(1);
        String queryUltimoMes = queryTempoMedio.replace("TO_CHAR(v.data_partida, 'YYYY-MM-DD') = ?", 
                "EXTRACT(MONTH FROM v.data_partida) = ? AND EXTRACT(YEAR FROM v.data_partida) = ?");
        
        // Calcular diferença do último ano
        LocalDate dataUltimoAno = data.minusYears(1);
        String queryUltimoAno = queryTempoMedio.replace("TO_CHAR(v.data_partida, 'YYYY-MM-DD') = ?", 
                "EXTRACT(MONTH FROM v.data_partida) = ? AND EXTRACT(YEAR FROM v.data_partida) = ?");
        
        try (Connection connection = databaseConfig.getConnection()) {
            // Calcular tempo médio
            try (PreparedStatement statement = connection.prepareStatement(queryTempoMedio)) {
                statement.setInt(1, linhaId);
                statement.setInt(2, estacaoPartida);
                statement.setInt(3, estacaoDestino);
                statement.setInt(4, estacaoDestino);
                statement.setInt(5, estacaoPartida);
                statement.setString(6, data.toString()); // Usando String ao invés de Date
                
                System.out.println("Debug - Query Tempo Médio: " + queryTempoMedio);
                System.out.println("Debug - Params: linha=" + linhaId + 
                                   ", estações=" + estacaoPartida + " e " + estacaoDestino + 
                                   ", data=" + data.toString());
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        double tempoMedioSegundos = resultSet.getDouble("tempo_medio");
                        System.out.println("Debug - Tempo médio em segundos: " + tempoMedioSegundos);
                        int minutos = (int) (tempoMedioSegundos / 60);
                        int segundos = (int) (tempoMedioSegundos % 60);
                        resultado.put("tempoMedio", String.format("%d:%02d Min", minutos, segundos));
                    } else {
                        System.out.println("Debug - Nenhum resultado encontrado para tempo médio");
                        System.out.println("Debug - SQL: " + queryTempoMedio + " com linhaId=" + linhaId + 
                                          ", estacao_partida=" + estacaoPartida + ", estacao_destino=" + estacaoDestino +
                                          ", data=" + data.toString());
                        resultado.put("tempoMedio", "0:00 Min");
                    }
                }
            }
            
            // Calcular tempo total
            try (PreparedStatement statement = connection.prepareStatement(queryTempoTotal)) {
                statement.setInt(1, linhaId);
                statement.setInt(2, estacaoPartida);
                statement.setInt(3, estacaoDestino);
                statement.setInt(4, estacaoDestino);
                statement.setInt(5, estacaoPartida);
                statement.setString(6, data.toString()); // Usando String ao invés de Date
                
                System.out.println("Debug - Query Tempo Total: " + queryTempoTotal);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        double tempoTotalSegundos = resultSet.getDouble("tempo_total");
                        System.out.println("Debug - Tempo total em segundos: " + tempoTotalSegundos);
                        int minutos = (int) (tempoTotalSegundos / 60);
                        resultado.put("tempoTotal", String.format("%d Min", minutos));
                    } else {
                        System.out.println("Debug - Nenhum resultado encontrado para tempo total");
                        resultado.put("tempoTotal", "0 Min");
                    }
                }
            }
            
            // Calcular diferença último mês
            try (PreparedStatement statement = connection.prepareStatement(queryUltimoMes)) {
                statement.setInt(1, linhaId);
                statement.setInt(2, estacaoPartida);
                statement.setInt(3, estacaoDestino);
                statement.setInt(4, estacaoDestino);
                statement.setInt(5, estacaoPartida);
                statement.setInt(6, dataUltimoMes.getMonthValue());
                statement.setInt(7, dataUltimoMes.getYear());
                
                System.out.println("Debug - Query Último Mês: " + queryUltimoMes);
                System.out.println("Debug - Params mês: " + dataUltimoMes.getMonthValue() + 
                                   ", ano: " + dataUltimoMes.getYear());
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    double tempoMedioAnteriorSegundos = 0;
                    if (resultSet.next()) {
                        tempoMedioAnteriorSegundos = resultSet.getDouble("tempo_medio");
                        System.out.println("Debug - Tempo médio mês anterior: " + tempoMedioAnteriorSegundos);
                    }
                    
                    double tempoAtualSegundos = 0;
                    String tempoMedio = (String) resultado.get("tempoMedio");
                    if (tempoMedio != null && !tempoMedio.equals("0:00 Min")) {
                        String[] partes = tempoMedio.split(":");
                        tempoAtualSegundos = Integer.parseInt(partes[0]) * 60 + Integer.parseInt(partes[1].split(" ")[0]);
                    }
                    System.out.println("Debug - Tempo atual segundos: " + tempoAtualSegundos);
                    
                    double diferencaSegundos = tempoMedioAnteriorSegundos - tempoAtualSegundos;
                    String sinal = diferencaSegundos > 0 ? "-" : "+";
                    diferencaSegundos = Math.abs(diferencaSegundos);
                    
                    int diferencaMinutos = (int) (diferencaSegundos / 60);
                    int diferencaSegs = (int) (diferencaSegundos % 60);
                    
                    resultado.put("diferencaMes", String.format("%s%d Seg", sinal, diferencaSegs));
                }
            }
            
            // Calcular diferença último ano
            try (PreparedStatement statement = connection.prepareStatement(queryUltimoAno)) {
                statement.setInt(1, linhaId);
                statement.setInt(2, estacaoPartida);
                statement.setInt(3, estacaoDestino);
                statement.setInt(4, estacaoDestino);
                statement.setInt(5, estacaoPartida);
                statement.setInt(6, dataUltimoAno.getMonthValue());
                statement.setInt(7, dataUltimoAno.getYear());
                
                System.out.println("Debug - Query Último Ano: " + queryUltimoAno);
                System.out.println("Debug - Params mês: " + dataUltimoAno.getMonthValue() + 
                                   ", ano: " + dataUltimoAno.getYear());
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    double tempoMedioAnteriorSegundos = 0;
                    if (resultSet.next()) {
                        tempoMedioAnteriorSegundos = resultSet.getDouble("tempo_medio");
                        System.out.println("Debug - Tempo médio ano anterior: " + tempoMedioAnteriorSegundos);
                    }
                    
                    double tempoAtualSegundos = 0;
                    String tempoMedio = (String) resultado.get("tempoMedio");
                    if (tempoMedio != null && !tempoMedio.equals("0:00 Min")) {
                        String[] partes = tempoMedio.split(":");
                        tempoAtualSegundos = Integer.parseInt(partes[0]) * 60 + Integer.parseInt(partes[1].split(" ")[0]);
                    }
                    System.out.println("Debug - Tempo atual segundos: " + tempoAtualSegundos);
                    
                    double diferencaSegundos = tempoMedioAnteriorSegundos - tempoAtualSegundos;
                    String sinal = diferencaSegundos > 0 ? "-" : "+";
                    diferencaSegundos = Math.abs(diferencaSegundos);
                    
                    int diferencaSegs = (int) diferencaSegundos;
                    
                    resultado.put("diferencaAno", String.format("%s%d Seg", sinal, diferencaSegs));
                }
            }
        }
        
        return resultado;
    }
}
