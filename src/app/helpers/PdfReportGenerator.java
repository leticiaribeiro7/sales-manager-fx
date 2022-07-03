package app.helpers;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.List;

import entities.Order;
import entities.Product;
import entities.Sale;
import entities.Supplier;

/**
 * Classe criada para gerar PDF a partir das listas
 * 
 *
 */
public class PdfReportGenerator {
	
	// Contadores para cada método que servem para não sobrescrever os relatórios já criados
	
	public static Integer saleCounter = 1;
	public static Integer supplierCounter = 1;
	public static Integer productCounter = 1;
	public static Integer clientCounter = 1;

	
	
	/**
	 * Gera PDF de fornecedores
	 * @param suppliers
	 */
	
	public static void supplierReport (List<Supplier> suppliers) {
		Document doc = new Document();
		String arq = String.format("relatorio_fornecedores_%s.pdf", supplierCounter);
		supplierCounter++;
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arq));
			doc.open();
			
			Paragraph p = new Paragraph("Relatório de Fornecedores");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(20);
			doc.add(p);
			
			
			
			PdfPTable table = new PdfPTable(5);
			
			PdfPCell cel1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell cel2 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell cel3 = new PdfPCell(new Paragraph("Cnpj"));
			PdfPCell cel4 = new PdfPCell(new Paragraph("Endereço"));
			PdfPCell cel5 = new PdfPCell(new Paragraph("Código Produtos"));
			
			
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			table.addCell(cel5);
			
			for(Supplier s : suppliers) {
				cel1 = new PdfPCell(new Paragraph(s.getId()+""));
				cel2 = new PdfPCell(new Paragraph(s.getName()+""));
				cel3 = new PdfPCell(new Paragraph(s.getCnpj()+""));
				cel4 = new PdfPCell(new Paragraph(s.getAddress()+""));
				cel5 = new PdfPCell(new Paragraph(s.getProductsId()+""));
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
				table.addCell(cel5);
			
			}
			
			doc.add(table);
			doc.close();
			Runtime.getRuntime().exec(new String[] {"xdg-open", arq}); // essa linha funciona apenas no Linux
			
			System.out.println("Relatório gerado com sucesso.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Gera PDF de vendas.
	 * @param sales
	 */
	public static void salesReport(List<Sale> sales) {
		
		
		Document doc = new Document();
		String arq = String.format("relatorio_vendas_%s.pdf", saleCounter);
		saleCounter++;
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arq));
			doc.open();
			
			Paragraph p = new Paragraph("Relatorio de Vendas");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(20);
			doc.add(p);
			
			
			Paragraph qtd = new Paragraph("Quantidade total de pratos vendidos: " + sales.size());
			doc.add(qtd);
			
			
			p = new Paragraph("");
			p.setSpacingAfter(20);
			doc.add(p);
			
			PdfPTable table = new PdfPTable(5);
			
			PdfPCell cel1 = new PdfPCell(new Paragraph("Codigo"));
			PdfPCell cel2 = new PdfPCell(new Paragraph("Data"));
			PdfPCell cel3 = new PdfPCell(new Paragraph("Pratos"));
			PdfPCell cel4 = new PdfPCell(new Paragraph("Valor"));
			PdfPCell cel5 = new PdfPCell(new Paragraph("Quantidade de itens: "));
			
			
			
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			table.addCell(cel5);
			
			for(Sale s : sales) {
				cel1 = new PdfPCell(new Paragraph(s.getId()+""));
				cel2 = new PdfPCell(new Paragraph(s.getDate()+""));
				cel3 = new PdfPCell(new Paragraph(s.getOrders()+""));
				cel4 = new PdfPCell(new Paragraph(s.getPrice()+""));
				cel5 = new PdfPCell(new Paragraph(s.getOrders().size()+""));
				
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
				table.addCell(cel5);
			
			}
			
			doc.add(table);
			doc.close();
			Runtime.getRuntime().exec(new String[] {"xdg-open", arq});
			
			System.out.println("Relatório gerado com sucesso.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Gera PDF do estoque.
	 * @param products
	 */
	public static void productsReport(List<Product> products) {
		Document doc = new Document();
		String arq = String.format("relatorio_estoque_%s.pdf", productCounter);
		productCounter++;
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arq));
			doc.open();
			
			Paragraph p = new Paragraph("Relatorio do Estoque");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(20);
			doc.add(p);
			
			
			
			PdfPTable table = new PdfPTable(4);
			
			PdfPCell cel1 = new PdfPCell(new Paragraph("Codigo"));
			PdfPCell cel2 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell cel3 = new PdfPCell(new Paragraph("Quantidade"));
			PdfPCell cel4 = new PdfPCell(new Paragraph("Validade"));
			
			
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			
			for(Product prod : products) {
				cel1 = new PdfPCell(new Paragraph(prod.getId()+""));
				cel2 = new PdfPCell(new Paragraph(prod.getName()+""));
				cel3 = new PdfPCell(new Paragraph(prod.getQuantity()+""));
				cel4 = new PdfPCell(new Paragraph(prod.getExpiration()+""));
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
			
			}
			
			doc.add(table);
			doc.close();
			
			Runtime.getRuntime().exec(new String[] {"xdg-open", arq});
			
			System.out.println("Relatório gerado com sucesso.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Gera nota do cliente ao finalizar uma venda
	 * @param sale
	 */
	public static void clientPurchase(Sale sale) {
		Document doc = new Document();
		String arq = String.format("nota_cliente_%s.pdf", clientCounter);
		clientCounter++;
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arq));
			doc.open();
			
			Paragraph p = new Paragraph("Nota do Cliente");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(20);
			doc.add(p);
			
	
			
			PdfPTable table = new PdfPTable(3);
			
			PdfPCell cel1 = new PdfPCell(new Paragraph("Codigo"));
			PdfPCell cel2 = new PdfPCell(new Paragraph("Item"));
			PdfPCell cel3 = new PdfPCell(new Paragraph("Valor"));
			
			
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
		
			
			for(Order ord : sale.getOrders()) {
				cel1 = new PdfPCell(new Paragraph(ord.getId()+""));
				cel2 = new PdfPCell(new Paragraph(ord.getName()+""));
				cel3 = new PdfPCell(new Paragraph(ord.getPrice()+""));
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
			
			}
			

			
			p = new Paragraph(String.format("Valor Total: %.2f", sale.getPrice()));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(20);
			
			doc.add(table);
			doc.add(p);
			doc.close();
			

			Runtime.getRuntime().exec(new String[] {"xdg-open", arq});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
