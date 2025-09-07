package br.ufrn.imd.giife.obspot.common.service.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "a/an %s already exists with the ID %d";

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String entity, Long id) {
        super(String.format(DEFAULT_MESSAGE, entity, id));
    }
}
