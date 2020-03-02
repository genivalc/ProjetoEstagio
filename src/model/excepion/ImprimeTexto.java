/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.excepion;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

/**
 *
 * @author geniv
 */
public class ImprimeTexto {

    String DOCUMENTO, IMPRESSORA = null;

    public ImprimeTexto() {

    }

    private void ImpressoraSelecionada(String printerName) {
        //Lista todas impressoras atÃ© encontrar a enviada por parametro
        PrintService serviceFound = null;
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService service : services) {
            if (service.getName().trim().equals(printerName.trim())) {
                serviceFound = service;
            } else {
                // serÃ¡ impressora na impressora padrÃ£o
                serviceFound = PrintServiceLookup.lookupDefaultPrintService();
            }
        }

    }

    public void imprimeDocumento(String documento, String printer) throws UnsupportedEncodingException, PrintException {

        String CABECARIO_MOINHO
                = "----------------------------------------------\n"
                + "            BOI FORTE \n"
                + "  RUA DO PRADO - 306 - CENTRO\n"
                + "        PATOS - PB\n"
                + "==============================================\n"
                + "         PRE VENDA - 000000 - DATA\n"
                + "0000 - NOME COMPLETO DO CLIENTE\n"
                + "NOME FANTASIA COMPLETO \n"
                + "ENDERECO COMPLETO DO CLIENTE\n"
                + "==============================================\n"
                + "COD e DESCRICAO DO PRODUTO\n"
                + "QTDe   X   UNITARIO       SUB-TOTAL\n"
                + "----------------------------------------------\n"
                + "";

        //  String impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService().getName();
        //  System.out.println("Impressora PadrÃ£o: " + impressoraPadrao);
        // PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        //Lista todas impressoras atÃ© encontrar a enviada por parametro
        PrintService impressora = null;
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService service : services) {

            System.out.println("Disponivel " + service.getName());

            if (service.getName().trim().equals(printer.trim())) {
                impressora = service;
                System.out.println("Impressora Localizada: " + impressora.getName());
            }
        }

        if (impressora == null) {
            // serÃ¡ impressora na impressora padrÃ£o
            impressora = PrintServiceLookup.lookupDefaultPrintService();
            System.out.println("Impressora NÃ£o Localizada Enviada para Impressora PadrÃ£o: " + impressora.getName());
        }

        // FileInputStream in = new FileInputStream(new File("C:\\teste.txt"));
        // InputStream in2 = new ByteArrayInputStream(CABECARIO_MOINHO.getBytes("UTF8"));
        InputStream in2 = new ByteArrayInputStream(documento.getBytes("UTF8"));

        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));

        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        // DocFlavor fff= DocFlavor.BYTE_ARRAY.AUTOSENSE;

        Doc doc = new SimpleDoc(in2, flavor, null);

        DocPrintJob job = impressora.createPrintJob();
        PrintJobWatcher pjw = new PrintJobWatcher(job);
        job.print(doc, pras);
        pjw.waitForDone();
        //in.close();

        // send FF to eject the page
        InputStream ff = new ByteArrayInputStream("\f".getBytes());
        Doc docff = new SimpleDoc(ff, flavor, null);
        DocPrintJob jobff = impressora.createPrintJob();
        pjw = new PrintJobWatcher(jobff);
        jobff.print(docff, null);
        pjw.waitForDone();

    }

    class PrintJobWatcher {

        boolean done = false;

        PrintJobWatcher(DocPrintJob job) {
            job.addPrintJobListener(new PrintJobAdapter() {
                public void printJobCanceled(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobCompleted(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobFailed(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobNoMoreEvents(PrintJobEvent pje) {
                    allDone();
                }

                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        System.out.println("ImpressÃ£o Enviada ...");
                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }

        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) throws PrintException, IOException {

        ImprimeTexto IP = new ImprimeTexto();

        IP.imprimeDocumento("Documento XX", "VOX(U)");

    }
}


