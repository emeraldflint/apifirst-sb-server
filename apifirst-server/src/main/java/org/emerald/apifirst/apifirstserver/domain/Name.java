package org.emerald.apifirst.apifirstserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Name {
    private String prefix;
    private String firstName;
    private String lastName;
    private String suffix;

}
