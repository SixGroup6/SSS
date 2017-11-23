package com.bwie.sss.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.bwie.sss.bean.FileInfoBean;

import com.bwie.sss.greendao.FileInfoBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig fileInfoBeanDaoConfig;

    private final FileInfoBeanDao fileInfoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        fileInfoBeanDaoConfig = daoConfigMap.get(FileInfoBeanDao.class).clone();
        fileInfoBeanDaoConfig.initIdentityScope(type);

        fileInfoBeanDao = new FileInfoBeanDao(fileInfoBeanDaoConfig, this);

        registerDao(FileInfoBean.class, fileInfoBeanDao);
    }
    
    public void clear() {
        fileInfoBeanDaoConfig.getIdentityScope().clear();
    }

    public FileInfoBeanDao getFileInfoBeanDao() {
        return fileInfoBeanDao;
    }

}
