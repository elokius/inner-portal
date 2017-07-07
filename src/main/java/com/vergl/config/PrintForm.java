package com.vergl.config;

import com.vergl.filling.model.Docstatus;
import com.vergl.filling.model.FilterType;
import com.vergl.filling.model.StatForm;
import com.vergl.raid.model.Division;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 11.04.17
 */
@Component
public class PrintForm {

    public File createOdsStatForm(StatForm statForm, Map<Division, List<String>> values, List<FilterType> filterTypes, Map<String, List<String>> summary) {
        File outputFile = null;

        if (statForm.getStatFormGroup().getSelectPart().getId() == 1) {
            outputFile = createIpStatForm(statForm, values, filterTypes, summary);
        } else if (statForm.getStatFormGroup().getSelectPart().getId() == 2) {
            outputFile = createIdStatForm(statForm, values, filterTypes, summary);
        }

        return outputFile;
    }

    private File createIpStatForm(StatForm statForm, Map<Division, List<String>> values, List<FilterType> filterTypes, Map<String, List<String>> summary) {
        File outputFile = null;

        try {
            // Загружаем образец
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("ods/ip_statform_template.ods");
            final File file = File.createTempFile("tmp", ".ods");
            file.deleteOnExit();
            try (FileOutputStream out = new FileOutputStream(file)) {
                IOUtils.copy(is, out);
            }
            final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);

            //Делаем рабочее поле 100х100 ячеек
            sheet.setRowCount(100);
            sheet.setColumnCount(100);

            // Изменяем название отчета
            sheet.getCellAt("A2").setValue(statForm.getName());

            // Заполняем общие сведения об отчете
            StringBuilder docstatuses = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            //Статусы ИП
            if (statForm.getDocstatuses() != null && statForm.getDocstatuses().size() > 0) {
                for (Docstatus docstatus : statForm.getDocstatuses()) {
                    docstatuses.append(docstatus.getCaption()).append(", ");
                }
                docstatuses.delete(docstatuses.length() - 2, docstatuses.length());
                sheet.getCellAt("D5").setValue(docstatuses.toString());
            } else {
                sheet.getCellAt("D5").setValue("Все статусы");
            }

            //Тип должника
            if (statForm.getDebtorType() != null) {
                sheet.getCellAt("D6").setValue(statForm.getDebtorType().getCaption());
            } else {
                sheet.getCellAt("D6").setValue("Не указан");
            }

            //Минимальная дата возбуждения
            if (statForm.getMinRisedate() != null) {
                sheet.getCellAt("D7").setValue(sdf.format(statForm.getMinRisedate()));
            } else {
                sheet.getCellAt("D7").setValue("Не указана");
            }

            //Максимальная дата возбуждения
            if (statForm.getMaxRisedate() != null) {
                sheet.getCellAt("D8").setValue(sdf.format(statForm.getMaxRisedate()));
            } else {
                sheet.getCellAt("D8").setValue("Не указана");
            }

            //Минимальная сумма долга
            if (statForm.getMinDebtsum() != 0) {
                sheet.getCellAt("D9").setValue(statForm.getMinDebtsum());
            } else {
                sheet.getCellAt("D9").setValue("Не указана");
            }

            //Максимальная сумма долга
            if (statForm.getMaxDebtsum() != 0) {
                sheet.getCellAt("D10").setValue(statForm.getMaxDebtsum());
            } else {
                sheet.getCellAt("D10").setValue("Не указана");
            }

            //Сущность исполнения
            if (statForm.getDebtClass() != null) {
                sheet.getCellAt("D11").setValue(statForm.getDebtClass().getCaption());
            } else {
                sheet.getCellAt("D11").setValue("Не указана");
            }

            //Дата актуальности
            if (statForm.getActualDate() != null) {
                sheet.getCellAt("D12").setValue(sdf.format(statForm.getActualDate()));
            } else {
                sheet.getCellAt("D12").setValue("Не указана");
            }

            //Дополнительная информация
            if (statForm.getAdditionalInfo() != null) {
                sheet.getCellAt("D13").setValue(statForm.getAdditionalInfo());
            } else {
                sheet.getCellAt("D13").setValue("Не указана");
            }

            //Заполняем шапку таблицы, ставим стили Header
            sheet.getCellAt(1, 14).setValue("Код отдела");
            sheet.getCellAt(1, 14).setStyleName("Header");
            sheet.getCellAt(2, 14).setValue("Отдел судебных приставов");
            sheet.getCellAt(2, 14).setStyleName("Header");
            for (int i = 0; i < filterTypes.size(); i++) {
                sheet.getCellAt(3 + i, 14).setValue(filterTypes.get(i).getCaption());
                sheet.getCellAt(3 + i, 14).setStyleName("Header");
            }

            int row = 15;
            int cell = 1;

            //Заполняем таблицу сведениями, заполняем стили Values

            fillTable(values, summary, sheet, row, cell);

            //Сохраняем во временный файл
            outputFile = new File("documents/template/buff.ods");
            sheet.getSpreadSheet().saveAs(outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile;
    }


    private File createIdStatForm(StatForm statForm, Map<Division, List<String>> values, List<FilterType> filterTypes, Map<String, List<String>> summary) {
        File outputFile = null;

        try {
            // Загружаем образец
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("ods/id_statform_template.ods");
            final File file = File.createTempFile("tmp", ".ods");
            file.deleteOnExit();
            try (FileOutputStream out = new FileOutputStream(file)) {
                IOUtils.copy(is, out);
            }
            final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);

            //Делаем рабочее поле 100х100 ячеек
            sheet.setRowCount(100);
            sheet.setColumnCount(100);

            // Изменяем название отчета
            sheet.getCellAt("A2").setValue(statForm.getName());

            // Заполняем общие сведения об отчете
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            //Дата актуальности
            if (statForm.getActualDate() != null) {
                sheet.getCellAt("D5").setValue(sdf.format(statForm.getActualDate()));
            } else {
                sheet.getCellAt("D5").setValue("Не указана");
            }

            //Дополнительная информация
            if (statForm.getAdditionalInfo() != null) {
                sheet.getCellAt("D6").setValue(statForm.getAdditionalInfo());
            } else {
                sheet.getCellAt("D6").setValue("Не указана");
            }

            //Заполняем шапку таблицы, ставим стили Header
            sheet.getCellAt(1, 7).setValue("Код отдела");
            sheet.getCellAt(1, 7).setStyleName("Header");
            sheet.getCellAt(2, 7).setValue("ОСП");
            sheet.getCellAt(2, 7).setStyleName("Header");
            for (int i = 0; i < filterTypes.size(); i++) {
                sheet.getCellAt(3 + i, 7).setValue(filterTypes.get(i).getCaption());
                sheet.getCellAt(3 + i, 7).setStyleName("Header");
            }

            int row = 8;
            int cell = 1;

            //Заполняем таблицу сведениями, заполняем стили Values
            fillTable(values, summary, sheet, row, cell);

            //Сохраняем во временный файл
            outputFile = new File("documents/template/buff.ods");
            sheet.getSpreadSheet().saveAs(outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile;
    }

    private void fillTable(Map<Division, List<String>> values, Map<String, List<String>> summary, Sheet sheet, int row, int cell) {
        for (Map.Entry<Division, List<String>> entry : values.entrySet()) {
            //Значение номера отдела
            sheet.getCellAt(cell, row).setValue(entry.getKey().getNumber());
            sheet.getCellAt(cell, row).setStyleName("Values");
            //Значение названия отдела
            sheet.getCellAt(cell + 1, row).setValue(entry.getKey().getCaption());
            sheet.getCellAt(cell + 1, row).setStyleName("Values");

            //Значения столбцов
            int tempCell = cell + 2;
            for (String s : entry.getValue()) {
                sheet.getCellAt(tempCell, row).setValue(s);
                sheet.getCellAt(tempCell, row).setStyleName("Values");
                tempCell++;
            }
            row++;
        }

        //Заполняем итоговую строку
        for (Map.Entry<String, List<String>> entry : summary.entrySet()) {
            sheet.getCellAt(cell, row).setValue(entry.getKey());
            sheet.getCellAt(cell, row).setStyleName("Header");
            sheet.getCellAt(cell + 1, row).setStyleName("Header");

            int tempCell = cell + 2;
            for (String s : entry.getValue()) {
                sheet.getCellAt(tempCell, row).setValue(s);
                sheet.getCellAt(tempCell, row).setStyleName("Header");
                tempCell++;
            }
            row++;
        }
    }

}
