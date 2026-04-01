package org.ra.business;

import org.ra.entity.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DocumentBusiness {
    private List<Document> documents;
    private static DocumentBusiness instance;

    private DocumentBusiness() {
        documents = new ArrayList<>();
    }

    public static DocumentBusiness getInstance() {
        if (instance == null) instance = new DocumentBusiness();
        return instance;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void displayDocumentsList() {
        if (documents.isEmpty()) {
            System.out.println("Danh sách rỗng");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-10s | %-10s |\n",
                "ID", "Name", "Size", "Downloads");
        System.out.println("------------------------------------------------------------------");
        documents.forEach(Document::displayData);
    }

    public void createDocument(Document doc) {
        boolean exists = documents.stream()
                .anyMatch(d -> d.getDocumentId().equals(doc.getDocumentId()));
        if (exists) throw new IllegalArgumentException("ID trùng");
        documents.add(doc);
    }

    public void updateDocumentById(String id, Scanner sc) {
        Document doc = documents.stream()
                .filter(d -> d.getDocumentId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID không tồn tại"));

        System.out.println("1. Sửa tên");
        System.out.println("2. Sửa size");
        System.out.println("3. Sửa downloads");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Tên mới: ");
                String name = sc.nextLine();
                if (name.isEmpty()) throw new IllegalArgumentException("Name rỗng");
                doc.setDocumentName(name);
                break;
            case 2:
                System.out.print("Size mới: ");
                double size = Double.parseDouble(sc.nextLine());
                if (size <= 0) throw new IllegalArgumentException("Size > 0");
                doc.setFileSize(size);
                break;
            case 3:
                System.out.print("Downloads mới: ");
                int dl = Integer.parseInt(sc.nextLine());
                if (dl < 0) throw new IllegalArgumentException("Downloads >=0");
                doc.setDownloads(dl);
                break;
            default:
                throw new IllegalArgumentException("Sai lựa chọn");
        }
    }

    public void deleteById(String id) {
        Document doc = documents.stream()
                .filter(d -> d.getDocumentId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID không tồn tại"));
        documents.remove(doc);
    }

    public List<Document> searchByName(String name) {
        List<Document> result = documents.stream()
                .filter(d -> d.getDocumentName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy");
        } else {
            result.forEach(Document::displayData);
            System.out.println("Tổng: " + result.size());
        }
        return result;
    }

    public List<Document> filterPopular() {
        List<Document> list = documents.stream()
                .filter(d -> d.getDownloads() >= 1000)
                .collect(Collectors.toList());

        list.forEach(Document::displayData);
        return list;
    }

    public List<Document> sortByDownloadsDesc() {
        List<Document> list = documents.stream()
                .sorted((a, b) -> Integer.compare(b.getDownloads(), a.getDownloads()))
                .collect(Collectors.toList());

        list.forEach(Document::displayData);
        return list;
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
