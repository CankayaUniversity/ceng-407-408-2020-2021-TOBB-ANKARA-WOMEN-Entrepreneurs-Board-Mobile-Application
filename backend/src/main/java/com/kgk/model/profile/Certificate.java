package com.kgk.model.profile;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
@DynamoDBTable(tableName = "Certificates")
public class Certificate {

    @NonNull
    private String certId; //hash key

    @NonNull
    private String userId; //range key

    @NonNull
    @NotBlank
    private String certName;

    private String certDesc;

    @NonNull
    private String certFile;

    @NonNull
    private Long validity; //date

    // Partition key
    @DynamoDBHashKey(attributeName = "certId")
    @DynamoDBAutoGeneratedKey
    @NonNull
    public String getCertId() {
        return certId;
    }

    public void setCertId(@NonNull String certId) {
        this.certId = certId;
    }

    @DynamoDBRangeKey(attributeName = "userId")
    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "certName")
    @NonNull
    public String getCertName() {
        return certName;
    }

    public void setCertName(@NonNull String certName) {
        this.certName = certName;
    }

    @DynamoDBAttribute(attributeName = "certDesc")
    public String getCertDesc() {
        return certDesc;
    }

    public void setCertDesc(String certDesc) {
        this.certDesc = certDesc;
    }

    @DynamoDBAttribute(attributeName = "certFile")
    @NonNull
    public String getCertFile() {
        return certFile;
    }

    public void setCertFile(@NonNull String certFile) {
        this.certFile = certFile;
    }

    @DynamoDBAttribute(attributeName = "validity")
    @NonNull
    public Long getValidity() {
        return validity;
    }

    public void setValidity(@NonNull Long validity) {
        this.validity = validity;
    }

}