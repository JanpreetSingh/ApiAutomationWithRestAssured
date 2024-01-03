package com.qa.pojo.users;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {

    private Integer page;
    
    @JsonProperty("per_page")
    private Integer perPage;
    
    private Integer total;
    
    @JsonProperty("total_pages")
    private Integer totalPages;
    
    @JsonProperty("data")
    private List<UserDetails> listOfUsers;
 

    public Integer getPage() {
    return page;
    }

    public void setPage(Integer page) {
    this.page = page;
    }

    public Integer getPerPage() {
    return perPage;
    }

    public void setPerPage(Integer perPage) {
    this.perPage = perPage;
    }

    public Integer getTotal() {
    return total;
    }

    public void setTotal(Integer total) {
    this.total = total;
    }

    public Integer getTotalPages() {
    return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
    }

    public List<UserDetails> getListOfUsers() {
    return listOfUsers;
    }

    public void ListOfUsers(List<UserDetails> listOfUsers) {
    this.listOfUsers = listOfUsers;
    }
}
