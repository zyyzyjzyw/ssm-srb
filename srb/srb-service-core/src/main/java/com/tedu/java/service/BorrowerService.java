package com.tedu.java.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tedu.java.pojo.Borrower;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.pojo.vo.BorrowerApprovalVO;
import com.tedu.java.pojo.vo.BorrowerDetailVO;
import com.tedu.java.pojo.vo.BorrowerVO;

/**
 * <p>
 * 借款人 服务类
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
public interface BorrowerService extends IService<Borrower> {

    void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId);

    Integer getStatusByUserId(Long userId);

    IPage<Borrower> listPage(Page<Borrower> pageParam, String keyword);

    BorrowerDetailVO getBorrowerDetailVOById(Long id);

    void approval(BorrowerApprovalVO borrowerApprovalVO);
}
