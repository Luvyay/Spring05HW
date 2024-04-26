package ru.gb.SpringJPA.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskStatus {
    NOT_STARTED("unbeginned"),
    IN_PROGRESS("running"),
    COMPLETED("completed");

    private String statusDescription;
}
