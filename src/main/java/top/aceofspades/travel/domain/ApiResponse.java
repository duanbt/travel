package top.aceofspades.travel.domain;

import lombok.Getter;

/**
 * @author duanbt
 * @version 1.0
 **/
@Getter
public class ApiResponse<T> {

    private Boolean success;

    private T data;

    private String errorMsg;

    private ApiResponse() {
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.data = data;
        apiResponse.success = true;
        return apiResponse;
    }

    public static ApiResponse success() {
        return success(null);
    }

    public static ApiResponse fail(String errorMsg) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.success = false;
        apiResponse.errorMsg = errorMsg;
        return apiResponse;
    }

}
