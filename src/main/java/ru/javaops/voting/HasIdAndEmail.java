package ru.javaops.voting;

import ru.javaops.voting.HasId;

public interface HasIdAndEmail extends HasId {
    String getEmail();
}