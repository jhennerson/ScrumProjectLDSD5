package com.ifsp.scrumProjectLDSD5.dto.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class UserDTOPasswordEmailMixin {
    @JsonIgnore
    abstract String getPassword();
    
    @JsonIgnore
    abstract String getEmail();
}
