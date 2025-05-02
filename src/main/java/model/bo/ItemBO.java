package model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.dao.FuncionarioDAO;
import model.dao.ItemDAO;
import model.entity.ItemEntity;

@ApplicationScoped
public class ItemBO {

    @Inject
    FuncionarioDAO funcionarioDAO;

    @Inject
    ItemDAO itemDAO;

    public Optional<ArrayList<ItemEntity>> getByFuncionarioID(Long funcionarioId) {
        try {
            return itemDAO.findByFuncionarioID(funcionarioId);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar itens: " + e.getMessage(), e);
        }
    }

    public void createItem(ItemEntity item) {
        try {
            if (item.getNome() == null || item.getNome().isEmpty()) {
                throw new IllegalArgumentException("Nome do item não pode ser nulo ou vazio");
            }
            if (item.getAbreviacao() == null || item.getAbreviacao().isEmpty()) {
                throw new IllegalArgumentException("Abreviação do item não pode ser nula ou vazia");
            }
            if (item.getUrl() == null || item.getUrl().isEmpty()) {
                throw new IllegalArgumentException("URL do item não pode ser nula ou vazia");
            }

            itemDAO.create(item);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar item: " + e.getMessage(), e);
        }
    }

    public void deleteItem(Long id) {
        try {
            itemDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar item: " + e.getMessage(), e);
        }
    }

    public void favoriteItem(Long id) {
        try {
            ItemEntity item = itemDAO.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

            itemDAO.setFavorito(id, !item.getFavorito());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao favoritar item: " + e.getMessage(), e);
        }
    }
}
