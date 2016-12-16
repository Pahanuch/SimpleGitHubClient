
package ua.pavlo.tikhonov.simplegithubclient.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BranchDTO {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("commit")
    @Expose
    private Commit_DTO commit;
    @SerializedName("protection")
    @Expose
    private ProtectionDTO protection;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The commit
     */
    public Commit_DTO getCommit() {
        return commit;
    }

    /**
     * @param commit The commit
     */
    public void setCommit(Commit_DTO commit) {
        this.commit = commit;
    }

    /**
     * @return The protection
     */
    public ProtectionDTO getProtection() {
        return protection;
    }

    /**
     * @param protection The protection
     */
    public void setProtection(ProtectionDTO protection) {
        this.protection = protection;
    }

}
