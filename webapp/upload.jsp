<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>Выбор XML файла</title>
    <style>
        body {
            background-color: #cafff4;
        }
        label {
            border: 1px solid black;
            border-radius: 2px;
            background-color: lightblue;
            transition-duration: 0.4s;
            padding: 1px 6px;
        }
        label:hover {
            background-color: #4caf50;
            color: white;
        }
        .preview {
            background-color: #effaff;
            padding: 1px 6px;
        }
        .submit-btn {
            border: 1px solid darkviolet;
            border-radius: 2px;
            background-color: #b794ff;
            padding: 1px 6px;
        }
        .submit-btn:hover {
            background-color: #ff4565;
            color: white;
        }
        .close-btn {
            border: 1px solid darkslategray;
            background-color: #43b49b;
            padding: 1px 6px;
        }
        .close-btn:hover {
            background-color: #f55a5a;
            color: white;
        }
    </style>
</head>
<body>
<h1 style="text-align: center">Выберите XML файл</h1>
<form action="controller" method="post" enctype="multipart/form-data">
    <input name="command" value="parse_xml" hidden>
    <table>
        <tr>
            <td>
                <label for="xml_file_choice">Выбрать XML</label>
                <input id="xml_file_choice" type="file" accept=".xml" name="xml_file" required hidden><br/>
            </td>
            <td>
                <label for="xsd_file_choice">Выбрать XSD для проверки</label>
                <input id="xsd_file_choice" type="file" accept=".xsd" name="xsd_file" hidden><br/>
            </td>
            <td>
                <input id="close_files" class="close-btn" type="button" value="Очистить">
            </td>
        </tr>
        <tr>
            <td>
                <div id="preview_xml" class="preview">
                    <p>XML файл не выбран</p>
                </div>
            </td>
            <td>
                <div id="preview_xsd" class="preview">
                    <p>XSD схема не выбрана</p>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <input id="parserDOM" type="radio" name="parser" value="DOM" checked>
                <label for="parserDOM">DOM</label>
                <input id="parserSAX" type="radio" name="parser" value="SAX">
                <label for="parserSAX">SAX</label>
                <input id="parserStAX" type="radio" name="parser" value="StAX">
                <label for="parserStAX">StAX</label>
            </td>
        </tr>
        <tr><td>
            <input id="submit_form" class="submit-btn" type="submit" value="Отправить">
        </td></tr>
    </table>
</form>
<script>
    $("#submit_form").click(function () {
        let file = document.getElementById("xml_file_choice");
        if (file.files.length === 0) {
            alert("XML файл не выбран!");
        }
    });

    $("#xml_file_choice").change(function () {
        let filePreview = previewSelectedFile(this.files, "XML файл не выбран", "text/xml");
        let preview = $("#preview_xml");
        preview.empty("p");
        preview.append(filePreview);
    });

    $("#xsd_file_choice").change(function () {
        let filePreview = previewSelectedFile(this.files, "XSD схема не выбрана", "application/xml");
        let preview = $("#preview_xsd");
        preview.empty("p");
        preview.append(filePreview);
    });

    function previewSelectedFile(currentFiles, defaultMessage, fileType) {
        let filePreview = document.createElement("p");
        filePreview.textContent = defaultMessage;
        if(currentFiles.length !== 0) {
            filePreview.textContent = currentFiles[0].name;
            if (currentFiles[0].type === fileType)
                filePreview.textContent += " (" + getFileSize(currentFiles[0].size) + ")";
            else
                filePreview.textContent += ": Не подходящий тип файла";
        }
        return filePreview;
    }

    function getFileSize(fileSize){
        if(fileSize < 1024) {
            return fileSize + " байт";
        } else if(fileSize > 1024 && fileSize < 1024*1024) {
            return (fileSize/1024).toFixed(1) + " КБ";
        } else {
            return (fileSize/1024*1024).toFixed(1) + " МБ";
        }
    }

    $("#close_files").click(function () {
        let clear = document.createElement("p");
        document.getElementById("xml_file_choice").value = "";
        clear.textContent = "XML файл не выбран";
        let preview = $("#preview_xml");
        preview.empty("p");
        preview.append(clear);
        clear = document.createElement("p");
        document.getElementById("xsd_file_choice").value = "";
        clear.textContent = "XSD схема не выбрана";
        preview = $("#preview_xsd");
        preview.empty("p");
        preview.append(clear);
    });
</script>
</body>
</html>