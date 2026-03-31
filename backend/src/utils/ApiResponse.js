/**
 * @desc Standard API Response format
 */
class ApiResponse {
    constructor(statusCode, data, message = "Success") {
        this.success = statusCode < 400;
        this.message = message;
        this.data = data;
        // statusCode is kept for internal use but usually excluded from final JSON if needed
        // this.statusCode = statusCode; 
    }
}

module.exports = ApiResponse;
