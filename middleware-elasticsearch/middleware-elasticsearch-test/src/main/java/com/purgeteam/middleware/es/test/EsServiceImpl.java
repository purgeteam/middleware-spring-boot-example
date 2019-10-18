package com.purgeteam.middleware.es.test;

import com.purgeteam.middleware.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@Service
public class EsServiceImpl implements UserService<EsUser> {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private UserRepository userRepository;

    @Override
    public List<EsUser> findUserAllList() {
        List<EsUser> users = elasticsearchTemplate.queryForList(new NativeSearchQueryBuilder().build(), EsUser.class);
        log.info("es查询 数据条数:{}", users.size());
        return users;
    }

    @Override
    public EsUser addUser(EsUser user) {
        EsUser save = userRepository.save(user);
        log.info("es 添加数据成功");
        return save;
    }

    @Override
    public Integer delUser(Integer id) {
        return null;
    }

    private void createIndex(){
        if (elasticsearchTemplate.indexExists(EsUser.INDEX_NAME)){
            elasticsearchTemplate.createIndex(EsUser.class);
        }
    }
}
