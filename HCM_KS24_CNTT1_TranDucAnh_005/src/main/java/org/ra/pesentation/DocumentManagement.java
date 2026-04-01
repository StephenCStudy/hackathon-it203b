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
            System.out.println(" ======= Quan ly tai lieu so =============");
            System.out.println("1. Hien thi danh sach toan bo tai lieu");
            System.out.println("2. Them moi tai lieu");
            System.out.println("3. Cap nhat thong tin tai lieu theo ma tai lieu (id)");
            System.out.println("4. Xoa tai lieu theo ma tai lieu");
            System.out.println("5. Tim kiem tai lieu theo ten");
            System.out.println("6. Loc danh sach tai lieu pho bien (luot tai >=1000)");
            System.out.println("7. Sap xep danh sach tai lieu giam dan theo luot tai");
            System.out.println("8. Thoat");

            System.out.print("Nhap lua chon cua ban: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    db.DisplayDocumentsList();
                    break;
                case 2:
                    // khi thêm tài liệu, hỏi người dùng thêm liên tục nhiều tài liệu (yes/no), bắt lỗi trùng lặp id, cho phép nhập lại
                    System.out.println("Ban muon them nhieu tai lieu (YES/NO): ");
                    String answer = scanner.nextLine().toLowerCase();
                    Document doc = new Document();
                    switch (answer) {
                        case "yes":
                            System.out.print("Nhap so lan: ");
                            int n = Integer.parseInt(scanner.nextLine());
                            for (int i = 0; i < n; i++) {
                                db.CreateDocument(doc.inputData(scanner));
                            }
                            break;
                        case "no":
                            db.CreateDocument(doc.inputData(scanner));
                            break;
                        default:
                            System.out.println("ban phai lua chon (YES/NO) !");
                            break;
                    }
                    break;
                case 3:
                    // khi nhập thông tin, cho phép người dùng lựa chọn cập nhật thông tin gì, không cho phép chỉnh sửa id
                    System.out.print("Nhap id muon chinh sua: ");
                    db.UpdateDocumentById(scanner.nextLine());
                    break;
                case 4:
                    // bắt lỗi không tìm thấy tài liệu in ra "mã tài liệu không tồn tại trong hệ thống" case 3 & 4
                    break;
                case 5:
                    System.out.println("Nhap ten muon tim kiem");
                    db.SreachDocumentByName(scanner.nextLine());
                    //5
                    break;
                case 6:
                    db.SortDownloadsByCount()
                    // 6
                    break;
                case 7:
                    // 7
                    break;
                case 8:
                    System.out.println("Ket thuc chuong trinh");
                    break;
                default:
                    System.out.println("Nhap sai lua chon, vui long nhap lai");
                    break;

            }
                } while (choice !=8);
            } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
