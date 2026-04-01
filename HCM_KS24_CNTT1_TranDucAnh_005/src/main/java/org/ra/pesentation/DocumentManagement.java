package org.ra.pesentation;

import org.ra.business.DocumentBusiness;
import org.ra.entity.Document;

import java.util.Scanner;

public class DocumentManagement {
    static void main() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        DocumentBusiness db = DocumentBusiness.getInstance();

        try {
            do {
                try {
                    System.out.println("\n===== QUAN LY TAI LIEU =====");
                    System.out.println("1. Hien thi danh sach");
                    System.out.println("2. Them tai lieu");
                    System.out.println("3. Cap nhat");
                    System.out.println("4. Xoa");
                    System.out.println("5. Tim kiem theo ten");
                    System.out.println("6. Loc >=1000 downloads");
                    System.out.println("7. Sap xep giam dan downloads");
                    System.out.println("8. Thoat");

                    choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            db.displayDocumentsList();
                            break;
                        case 2:
                            while (true) {
                                Document doc = new Document();
                                db.createDocument(doc.inputData(scanner, db.getDocuments()));

                                System.out.print("Tiep tuc? (y/n): ");
                                if (!scanner.nextLine().equalsIgnoreCase("y")) break;
                            }
                            break;
                        case 3:
                            System.out.print("Nhap ID: ");
                            db.updateDocumentById(scanner.nextLine(), scanner);
                            break;
                        case 4:
                            System.out.print("Nhap ID: ");
                            db.deleteById(scanner.nextLine());
                            break;
                        case 5:
                            System.out.print("Nhap ten: ");
                            db.searchByName(scanner.nextLine());
                            break;
                        case 6:
                            db.filterPopular();
                            break;
                        case 7:
                            db.sortByDownloadsDesc();
                            break;
                        case 8:
                            System.out.println("Thoat");
                            break;
                        default:
                            System.out.println("Sai lua chon");
                    }
                } catch (Exception e) {
                    System.out.println("Loi: " + e.getMessage());
                    choice = 0;
                }
            } while (choice != 8);
        } finally {}
    }
}
