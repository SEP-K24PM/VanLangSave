package com.vls.postsearchservice.repository;

import com.vls.postsearchservice.dto.postelastic;
import java.util.List;

public interface PostDAO {
    public List<postelastic> search(String search);
}
