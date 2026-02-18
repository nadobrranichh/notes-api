package com.nadobranich.notes_api.mapping;

public interface Mapper <A,B>{
    B mapTo(A a);
    A mapFrom(B b);
}
