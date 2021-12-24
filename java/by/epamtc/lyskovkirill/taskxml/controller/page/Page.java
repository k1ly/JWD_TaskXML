package by.epamtc.lyskovkirill.taskxml.controller.page;

public enum Page {
    ERROR_PAGE("/WEB-INF/error/error.jsp"),
    RESULT_PAGE("/result.jsp"),
    UPLOAD_PAGE("/upload.jsp");

    private final String value;

    Page(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
