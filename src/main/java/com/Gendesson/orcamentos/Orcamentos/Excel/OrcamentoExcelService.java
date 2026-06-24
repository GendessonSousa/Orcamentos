package com.Gendesson.orcamentos.Orcamentos.Excel;

import com.Gendesson.orcamentos.ItemOrcamento.ItemOrcamentoModel;
import com.Gendesson.orcamentos.Orcamentos.OrcamentoModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
public class OrcamentoExcelService {

    public byte[] gerar(OrcamentoModel orcamento) {
        try {
            InputStream is = getClass().getResourceAsStream("/templates/excel/orcamento_template.xlsx");
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            // =========================
            // 🔹 1. Substituir placeholders
            // =========================
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        String value = cell.getStringCellValue();

                        value = substituir(value, "{{cliente_nome}}", orcamento.getCliente().getNome());
                        value = substituir(value, "{{cliente_endereco}}", orcamento.getCliente().getEndereco());
                        value = substituir(value, "{{cliente_telefone}}", orcamento.getCliente().getTelefone());
                        value = substituir(value, "{{cliente_email}}", orcamento.getCliente().getEmail());
                        value = substituir(value, "{{orcamento_data}}", orcamento.getDataCriacao().toString());

                        cell.setCellValue(value);
                    }
                }
            }

            // =========================
            // 🔹 2. Tabela de itens (mantendo formatação)
            // =========================

            int templateRowIndex = 17; // linha modelo no Excel
            int startRow = templateRowIndex;

            Row templateRow = sheet.getRow(templateRowIndex);

            for (ItemOrcamentoModel item : orcamento.getItens()) {

                Row newRow;

                // 🔥 Usa a primeira linha como base (sem criar nova)
                if (startRow == templateRowIndex) {
                    newRow = sheet.getRow(startRow);
                } else {
                    newRow = sheet.createRow(startRow);
                }

                // 🔹 Copiar estilo das células
                for (int i = 0; i < templateRow.getLastCellNum(); i++) {
                    Cell oldCell = templateRow.getCell(i);
                    Cell newCell = newRow.getCell(i);

                    if (newCell == null) {
                        newCell = newRow.createCell(i);
                    }

                    if (oldCell != null) {
                        newCell.setCellStyle(oldCell.getCellStyle());
                    }
                }

                // 🔹 Preencher dados
                newRow.getCell(0).setCellValue(item.getQuantidade().doubleValue());
                newRow.getCell(1).setCellValue(item.getServico().getUnidade());
                newRow.getCell(2).setCellValue(item.getServico().getNome());
                newRow.getCell(6).setCellValue(item.getPrecoUnit().doubleValue());
                newRow.getCell(7).setCellValue(item.getSubtotal().doubleValue());

                startRow++;
            }

            int linhaTotal = 44; // ajusta se necessário
            Row totalRow = sheet.getRow(linhaTotal);

            Cell cellTotal = totalRow.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cellTotal.setCellValue(orcamento.getValorTotal().doubleValue());


            // =========================
            // 🔹 3. Gerar arquivo
            // =========================
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar Excel", e);
        }
    }

    // =========================
    // 🔹 Método auxiliar
    // =========================
    private String substituir(String texto, String placeholder, String valor) {
        return texto.replace(placeholder, valor != null ? valor : "");
    }
}