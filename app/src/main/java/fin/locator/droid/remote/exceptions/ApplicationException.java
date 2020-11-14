package fin.locator.droid.remote.exceptions;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.List;

import fin.locator.droid.remote.exceptions.errors.ErrorModel;

public class ApplicationException extends IOException {


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("error")
    @Expose
    private List<ErrorModel> error = null;


    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable clause) {
        super(clause);
    }

    public ApplicationException(String message, Throwable clause) {
        super(message, clause);

    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ErrorModel> getError() {
        return error;
    }

    public void setError(List<ErrorModel> error) {
        this.error = error;
    }


    public String getErrorMessage() {
        if (error != null) {
            if (!error.isEmpty()) {
                if (error.size() == 1) {
                    return error.get(0).getDescription();
                } else {
                    return TextUtils.join("\n", error);
                }
            }

            return description;
        }

        return description;
    }



}
