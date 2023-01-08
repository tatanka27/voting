package ru.javaops.voting.model;

public interface HasIdAndEmail extends HasId {
    String getEmail();
}