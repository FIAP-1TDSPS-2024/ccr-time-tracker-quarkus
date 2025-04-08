package model.entity;

import jakarta.ws.rs.core.Response;

public class ResponseEntity {
    private String message;
    private String code;
    private String slug;
    private Object data;

    public ResponseEntity() {
    }

    public ResponseEntity(String message, String code, String slug) {
        this.message = message;
        this.code = code;
        this.slug = slug;
    }

    public ResponseEntity(String message, String code, String slug, Object data) {
        this.message = message;
        this.code = code;
        this.slug = slug;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response OK(Object data) {
        return Response.ok(new ResponseEntity("Success", "0000", "success", data)).build();
    }

    public Response Created(Object data) {
        return Response.status(Response.Status.CREATED)
                .entity(new ResponseEntity("Resource created", "0000", "created", data)).build();
    }

    public Response NoContent() {
        return Response.noContent().build();
    }

    public Response NotFound() {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ResponseEntity("Resource not found", "0001", "not_found")).build();
    }

    public Response InternalServerError() {
        return Response.serverError().entity(new ResponseEntity("Internal Server Error", "0001", "internal_error"))
                .build();
    }

    public Response BadRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ResponseEntity(message, "0002", "bad_request")).build();
    }

}
