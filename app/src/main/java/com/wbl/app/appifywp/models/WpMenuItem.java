package com.wbl.app.appifywp.models;

import java.util.ArrayList;

public class WpMenuItem {
    private float term_id;
    private String name;
    private String slug;
    private float term_group;
    private float term_taxonomy_id;
    private String taxonomy;
    private String description;
    private float parent;
    private float count;
    private String filter;
    ArrayList<WpMenuChildItem> items = new ArrayList<>();


    // Getter Methods

    public float getTerm_id() {
        return term_id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public float getTerm_group() {
        return term_group;
    }

    public float getTerm_taxonomy_id() {
        return term_taxonomy_id;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public String getDescription() {
        return description;
    }

    public float getParent() {
        return parent;
    }

    public float getCount() {
        return count;
    }

    public String getFilter() {
        return filter;
    }

    public ArrayList<WpMenuChildItem> getItems() {
        return items;
    }

    // Setter Methods

    public void setTerm_id(float term_id) {
        this.term_id = term_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setTerm_group(float term_group) {
        this.term_group = term_group;
    }

    public void setTerm_taxonomy_id(float term_taxonomy_id) {
        this.term_taxonomy_id = term_taxonomy_id;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParent(float parent) {
        this.parent = parent;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setItems(ArrayList<WpMenuChildItem> items) {
        this.items = items;
    }
}
