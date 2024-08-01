package org.emerald.apifirst.apifirstserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Dimensions {
    private Integer length;
    private Integer width;
    private Integer height;
}
