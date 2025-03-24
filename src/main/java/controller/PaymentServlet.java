package controller;

import Dao.DaoPay;
import config.PayPalConfig;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Paypal;

@WebServlet({"/payment"})
public class PaymentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/client/home/Paypal.jsp").forward(request, response);
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String cancelUrl = "http://localhost:8080/cancel";
    String successUrl = "http://localhost:8080/success";

    try {
        // Lấy dữ liệu từ form
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String phone = request.getParameter("phone");

        // Kiểm tra và chuyển đổi giá trị
        if (priceStr == null || quantityStr == null || phone == null || phone.trim().isEmpty()) {
            response.getWriter().println("Dữ liệu không hợp lệ!");
            return;
        }

        double price = Double.parseDouble(priceStr);
        int quantity = Integer.parseInt(quantityStr);

        if (price <= 0 || quantity <= 0) {
            response.getWriter().println("Giá hoặc số lượng phải lớn hơn 0!");
            return;
        }

        // Khởi tạo PayPal API Context
        APIContext apiContext = PayPalConfig.getAPIContext();

        // Tạo đối tượng Paypal và lưu vào DB
        Paypal paypal = new Paypal((int) price, quantity, phone);
        DaoPay daoPay = new DaoPay();
        daoPay.addLocation(paypal);

        // Thiết lập thông tin thanh toán
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.2f", price));

        Transaction transaction = new Transaction();
        transaction.setDescription("Thanh toán tour du lịch");
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        // Cài đặt phương thức thanh toán
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        // Tạo Payment và cấu hình redirect
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        // Tạo thanh toán PayPal
        Payment createdPayment = payment.create(apiContext);

        // Lấy link xác nhận thanh toán
        for (Links link : createdPayment.getLinks()) {
            if ("approval_url".equalsIgnoreCase(link.getRel())) {
                response.sendRedirect(link.getHref());
                return;
            }
        }

        response.getWriter().println("Không tìm thấy đường dẫn thanh toán!");

    } catch (NumberFormatException e) {
        response.getWriter().println("Lỗi: Giá hoặc số lượng không hợp lệ!");
        e.printStackTrace();
    } catch (PayPalRESTException e) {
        response.getWriter().println("Lỗi khi tạo thanh toán: " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        response.getWriter().println("Lỗi không xác định: " + e.getMessage());
        e.printStackTrace();
    }
}
}
