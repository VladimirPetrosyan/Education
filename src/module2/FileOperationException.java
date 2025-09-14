package module2;

/** Собственное исключение для ошибок работы с файлами */
public class FileOperationException extends Exception {
    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
