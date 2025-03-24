package config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

import java.util.HashMap;
import java.util.Map;

public class PayPalConfig {
    // Khai báo Client ID, Secret và chế độ sandbox/live
    private static final String CLIENT_ID = "Ae1gNvniG4xbGsr_e5joj0m5WGau5kisTS_4VWlz82K9c4FnKE-kE-OhXYyEbBk-8IM_k3BGrNisndJm";
    private static final String CLIENT_SECRET = "ECw5Z64MsnR0sdGllWizVo-NaJEPbaoN0xOJgP4rdxri6_fv1g_14J4N32FnwSSrTHvEvWzrK71XLXRP";
    private static final String MODE = "sandbox"; // Đổi thành "live" khi deploy thật

    public static APIContext getAPIContext() throws PayPalRESTException {
        // Cấu hình PayPal SDK
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", MODE);

        // Lấy access token từ PayPal
        OAuthTokenCredential tokenCredential = new OAuthTokenCredential(CLIENT_ID, CLIENT_SECRET, sdkConfig);
        String accessToken = tokenCredential.getAccessToken();

        // Tạo APIContext với accessToken + ClientId + ClientSecret để tránh lỗi
        APIContext apiContext = new APIContext(accessToken);
        apiContext.setConfigurationMap(sdkConfig);

        return apiContext;
    }
}
