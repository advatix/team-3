package com.schedular.service;

import com.schedular.domain.ApplicationInfo;
import com.schedular.dto.ApplicationInfoConfigMappedDetails;

public interface IApplicationDao {

  ApplicationInfo findById(Integer id);

  ApplicationInfoConfigMappedDetails getMappedInfoById(Integer id);

}
