package taglib;

import by.epamtc.lyskovkirill.taskxml.bean.Certificate;
import by.epamtc.lyskovkirill.taskxml.bean.Dosage;
import by.epamtc.lyskovkirill.taskxml.bean.Medicine;
import by.epamtc.lyskovkirill.taskxml.bean.Package;
import by.epamtc.lyskovkirill.taskxml.bean.constants.PackageType;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListViewerTag extends TagSupport {
    private List<Medicine> list;

    public void setList(List<Medicine> list) {
        this.list = list;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if (list != null) {
                int counter = 0;
                out.println("<table>");
                out.println("<tr><th/>");
                out.println("<td>Название</td>");
                out.println("<td>Производитель</td>");
                out.println("<td>Группа</td>");
                out.println("<td>Аналоги</td>");
                out.println("<td>Вариации</td>");
                out.println("<td>Сертификат</td>");
                out.println("<td>Упаковка</td>");
                out.println("<td>Дозировка</td>");
                out.println("</tr>");

                for (Medicine medicine : list) {
                    out.println("<tr><th>" + (++counter) + "</th>");
                    out.println("<td>" + medicine.getName() + "</td>");
                    out.println("<td>" + medicine.getPharma() + "</td>");
                    out.println("<td>" + medicine.getGroup().getName() + "</td>");

                    out.println("<td>");
                    List<String> analogs = medicine.getAnalogs();
                    for (int i = 0; i < analogs.size(); i++) {
                        out.print(analogs.get(i));
                        if (i != analogs.size() - 1)
                            out.println(",<br/>");
                    }
                    out.println("</td>");

                    out.println("<td>");
                    List<PackageType> versions = medicine.getVersions();
                    for (int i = 0; i < versions.size(); i++) {
                        out.print(versions.get(i).getName());
                        if (i != versions.size() - 1)
                            out.println("<br/>");
                    }
                    out.println("</td>");

                    out.println("<td>");
                    Certificate certificate = medicine.getCertificate();
                    out.println("Номер: ");
                    out.println(certificate.getNumber());
                    out.println("<br/>");
                    out.println("Дата выдачи: ");
                    out.println(new SimpleDateFormat("dd.MM.yyyy").format(certificate.getIssueDate()));
                    out.println("<br/>");
                    out.println("Дата истечения: ");
                    out.println(new SimpleDateFormat("dd.MM.yyyy").format(certificate.getExpiryDate()));
                    out.println("<br/>");
                    out.println("Организация: ");
                    out.println(certificate.getOrganization());
                    out.println("</td>");

                    out.println("<td>");
                    Package pack = medicine.getPack();
                    out.println("Тип: ");
                    out.println(pack.getType().getName());
                    out.println("<br/>");
                    out.println("Количество: ");
                    out.println(pack.getQuantity());
                    out.println("<br/>");
                    out.println("Цена: ");
                    out.println(pack.getPrice());
                    out.println("</td>");

                    out.println("<td>");
                    Dosage dosage = medicine.getDosage();
                    out.println("Количество: ");
                    out.println(dosage.getAmount());
                    out.println("<br/>");
                    out.println("Периодичность: ");
                    out.println(dosage.getPeriod());
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
