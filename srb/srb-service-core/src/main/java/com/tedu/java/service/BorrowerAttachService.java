package com.tedu.java.service;

import com.tedu.java.pojo.BorrowerAttach;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.pojo.vo.BorrowerAttachVO;

import java.util.List;

/**
 * <p>
 * 借款人上传资源表 服务类
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
public interface BorrowerAttachService extends IService<BorrowerAttach> {

    List<BorrowerAttachVO> selectBorrowerAttachVOList(Long id);
}
