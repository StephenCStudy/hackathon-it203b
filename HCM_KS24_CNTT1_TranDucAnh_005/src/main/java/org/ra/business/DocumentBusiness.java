package org.ra.business;

import org.ra.entity.Document;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DocumentBusiness {
    private List<Document> documents;
    private static DocumentBusiness instance;

    private DocumentBusiness() {}

    public static DocumentBusiness getInstance() {
        if (instance == null) {
            instance = new DocumentBusiness();
        }
        return instance;
    }
    public void DisplayDocumentsList() {
        if (documents.isEmpty()) {
            System.out.println("Danh sach hien tai rong");
            return;
        } else {
            documents.forEach(Document::displayData);
        }
    }

    public void CreateDocument(Document doc) throws IllegalAccessException {
        if (documents.stream().anyMatch(d -> d.getDocumentId().equals(doc.getDocumentId()))) {
            throw new IllegalAccessException("Id bi trung lap, vui long nhap id khac");
        } else {
            documents.add(doc);
        }
    }

    public void UpdateDocumentById(String idupd) throws IllegalAccessException {
        if(documents.stream().anyMatch(d -> d.getDocumentId().equals(idupd))) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Lua chon thong tin muon cap nhat");
            System.out.println("1. chinh sua ten tai lieu ");
            System.out.println("2. chinh sua file size  ");
            System.out.println("3. chinh sua luot tai  ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Nhap ten tai lieu moi: ");
                    documents.stream().filter(d -> d.getDocumentId().equals(idupd)).forEach(d -> {
                        d.setDocumentName(sc.nextLine());
                    });
                    break;
                case 2:
                    System.out.print("Nhap file size moi: ");
                    documents.stream().filter(d -> d.getDocumentId().equals(idupd)).forEach(d -> {
                       d.setFileSize(Integer.parseInt(sc.nextLine()));
                    });
                case 3:
                    System.out.print("Nhap luot tai moi: ");
                    documents.stream().filter(d -> d.getDocumentId().equals(idupd)).forEach(d -> {
                        d.setDownloads(Integer.parseInt(sc.nextLine()));
                    });
                    break;
                default:
                    throw new IllegalArgumentException("Lua sai muc, vui long nhap lai");
            }
        } else {
            throw new IllegalAccessException( idupd + " khong ton tai, vui long nhap [id] khac !");
        }
    }

    public void DeleteDocumentById(Document document) throws IllegalAccessException {
        if (documents.stream().anyMatch(d -> d.getDocumentId().equals(document.getDocumentId()))) {
            documents.remove(document);
        } else  {
            throw new IllegalAccessException(document.getDocumentId() + " khong ton tai");
        }
    }

    public List<Document> SreachDocumentByName(String documentName) {
        return documents.stream()
                .filter(d -> d.getDocumentName().toLowerCase().contains(documentName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void SortDocumentByDownloads(Document document) {
    }

    public List<Document> SortDownloadsByCount(int DownloadCount) {
        return documents.stream()
                .filter(d -> d.getDownloads() > 1000).collect(Collectors.toList());
    }
}

// quản lý list, áp dụng design pattern, dùng java 8+ (stream API, lambda, Optional)
// - hiển thị danh sách dạng bảng, nếu rỗng in lỗi
// - thêm tài liệu, ếu trùng id ghi lỗi trùng lặp
// - cập nhật thông tin, cho người dùng lựa chọn thông tin muốn cập nhật theo id
// - tìm kiếm theo tên, tìm kiếm tương đối(không hoa thuong) -> trả về đối tượng ? in lỗi 'hiển thị chi tiết thông tin và tổng số tài liệu tìm thấy'
// - xóa tài liệu (nếu mã tài liệu không có) in lỗi
// - sắp xếp theo lượt tải giảm dần, trả về danh sách, in ra màng hình
// - lọc tài liệu có downloads >=1000
