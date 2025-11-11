package com.bite.book_demo.enums;

public enum BookStatusEnum {
    DELETED(0, "删除"),
    NORMAL(1, "正常"),
    FORBIDDEN(2, "不允许借阅");

    private Integer code;
    private String desc;

    BookStatusEnum(Integer code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BookStatusEnum getStatusByCode(Integer code) {
        return switch (code) {
            case 0 -> BookStatusEnum.DELETED;
            case 1 -> BookStatusEnum.NORMAL;
            case 2 -> BookStatusEnum.FORBIDDEN;
            default -> null;
        };
    }
}
