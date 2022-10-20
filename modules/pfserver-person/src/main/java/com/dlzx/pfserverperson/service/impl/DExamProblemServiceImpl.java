package com.dlzx.pfserverperson.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.DDriverExam;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DExamProblemMapper;
import com.dlzx.pfserverperson.domain.DExamProblem;
import com.dlzx.pfserverperson.service.IDExamProblemService;
import org.springframework.transaction.annotation.Transactional;
import com.dlzx.common.core.exception.CustomException;

/**
 * 驾驶员-题库Service业务层处理
 *
 * @author dlzx
 * @date 2020-11-18
 */
@Service
public class DExamProblemServiceImpl implements IDExamProblemService {
    @Autowired
    private DExamProblemMapper dExamProblemMapper;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询驾驶员-题库
     *
     * @param id 驾驶员-题库ID
     * @return 驾驶员-题库
     */
    @Override
    public DExamProblem selectDExamProblemById(String id) {
        return dExamProblemMapper.selectDExamProblemById(id);
    }

    /**
     * 查询驾驶员-题库列表
     *
     * @param dExamProblem 驾驶员-题库
     * @return 驾驶员-题库
     */
    @Override
    public List<DExamProblem> selectDExamProblemList(DExamProblem dExamProblem) {
        return dExamProblemMapper.selectDExamProblemList(dExamProblem);
    }

    /**
     * 查询驾驶员-题库列表
     *
     * @return 驾驶员-题库集合
     */
    @Override
    public List<DExamProblem> selectDExamProblemList1() {
        return dExamProblemMapper.selectDExamProblemList1();
    }

    /**
     * 新增驾驶员-题库
     *
     * @param dExamProblem 驾驶员-题库
     * @return 结果
     */
    @Override
    public int insertDExamProblem(DExamProblem dExamProblem) {
        dExamProblem.setCreateTime(DateUtils.getNowDate());
        return dExamProblemMapper.insertDExamProblem(dExamProblem);
    }

    /**
     * 修改驾驶员-题库
     *
     * @param dExamProblem 驾驶员-题库
     * @return 结果
     */
    @Override
    public int updateDExamProblem(DExamProblem dExamProblem) {
        dExamProblem.setUpdateTime(DateUtils.getNowDate());
        return dExamProblemMapper.updateDExamProblem(dExamProblem);
    }

    /**
     * 批量删除驾驶员-题库
     *
     * @param ids 需要删除的驾驶员-题库ID
     * @return 结果
     */
    @Override
    public int deleteDExamProblemByIds(String[] ids) {
        return dExamProblemMapper.deleteDExamProblemByIds(ids);
    }

    /**
     * 删除驾驶员-题库信息
     *
     * @param id 驾驶员-题库ID
     * @return 结果
     */
    @Override
    public int deleteDExamProblemById(String id) {
        return dExamProblemMapper.deleteDExamProblemById(id);
    }

    /**
     * 导入驾驶员-题库信息
     *
     * @param problemList 试题集合
     * @return 结果
     */
    @Transactional
    @Override
    public Map<String, String> importUser(List<DExamProblem> problemList) {
        Map<String, String> result = new HashMap<>();
        if (StringUtils.isNull(problemList) || problemList.size() == 0) {
            throw new CustomException("导入数据为空！");
        }
        //需要新增的数据
        List<DExamProblem> insList = new ArrayList<>();
        //需要修改的数据
        List<DExamProblem> updList = new ArrayList<>();

        List<DExamProblem> listA = dExamProblemMapper.selectDExamProblemList1();

        StringBuilder errorInfo = new StringBuilder();
        StringBuilder errorInfo1 = new StringBuilder();
        StringBuilder errorInfo2 = new StringBuilder();
        StringBuilder errorInfo3 = new StringBuilder();
        StringBuilder errorInfo4 = new StringBuilder();
        StringBuilder errorInfo5 = new StringBuilder();
        StringBuilder errorInfo6 = new StringBuilder();
        StringBuilder errorInfo7 = new StringBuilder();
        StringBuilder errorInfo8 = new StringBuilder();
        StringBuilder errorInfo9 = new StringBuilder();
        boolean hasError = false;
        int countFlag = 0;

        try {
            for (int i = 0; i < problemList.size(); i++) {
                boolean f = problemList.get(i).equals();

                if (f) {
                    break;
                }
                countFlag = i+1;
            }
            for (int i = 0; i < countFlag; i++) {
                DExamProblem pro = problemList.get(i);
                String name = pro.getName();
                Long type = pro.getType();
                String opA = pro.getOptionA();
                String opB = pro.getOptionB();
                String opC = pro.getOptionC();
                String opD = pro.getOptionD();
                String answer = pro.getAnswer();
                if (StringUtils.isEmpty(name)) {
                    errorInfo.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (type == null) {
                    errorInfo1.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (type == 1 || type == 2) {
                    if (StringUtils.isEmpty(opA)) {
                        errorInfo2.append((i + 2) + "行");
                        hasError = true;
                        break;
                    }
                    if (StringUtils.isEmpty(opB)) {
                        errorInfo3.append((i + 2) + "行");
                        hasError = true;
                        break;
                    }
                    if (StringUtils.isEmpty(opC)) {
                        errorInfo4.append((i + 2) + "行");
                        hasError = true;
                        break;
                    }
                    if (StringUtils.isEmpty(opD)) {
                        errorInfo5.append((i + 2) + "行");
                        hasError = true;
                        break;
                    }
                }
                if (StringUtils.isEmpty(answer)) {
                    errorInfo6.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (type == 1) {
                    if (!answer.equals("A")) {

                        if (!answer.equals("B")) {

                            if (!answer.equals("C")) {
                                if (!answer.equals("D")) {
                                    errorInfo7.append((i + 2) + "行");
                                    hasError = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (type == 2) {
                    String[] n = answer.split(",");
                    for (int x = 0; x < n.length; x++) {
                        String s = n[x];
                        if (!s.equals("A")) {
                            if (!s.equals("B")) {
                                if (!s.equals("C")) {
                                    if (!s.equals("D")) {
                                        errorInfo8.append((i + 2) + "行");
                                        hasError = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if (type == 3) {
                    if (!answer.equals("A")) {
                        if (!answer.equals("B")) {
                            errorInfo9.append((i + 2) + "行");
                            hasError = true;
                            break;
                        }
                    }
                }
                List<DExamProblem> list3 = listA.stream().filter(a -> Objects.equals(name, a.getName()) && Objects.equals(type, a.getType()) && Objects.equals(opA, a.getOptionA()) && Objects.equals(opB, a.getOptionB()) && Objects.equals(opC, a.getOptionC()) && Objects.equals(opD, a.getOptionD())
                        && Objects.equals(answer, a.getAnswer())).collect(Collectors.toList());
                if (hasError) {
                    continue;
                }
                LoginUser userInfo = tokenService.getLoginUser();
                if (list3.size() == 0) {
                    String Id = UUID.randomUUID().toString();
                    pro.setId(Id);
                    pro.setCreateBy(userInfo.getUsername());
                    if (type == 2 || type == 3) {
                        pro.setScore(2);
                    } else if (type == 1) {
                        pro.setScore(1);
                    }
                    pro.setDeleteFlag(Long.valueOf(1));
                    insList.add(pro);

                } else if (list3.size() > 0 && list3.get(0).getDeleteFlag() == 2) {
                    String Id = UUID.randomUUID().toString();
                    pro.setId(Id);
                    pro.setCreateBy(userInfo.getUsername());
                    if (type == 2 || type == 3) {
                        pro.setScore(2);
                    } else if (type == 1) {
                        pro.setScore(1);
                    }
                    pro.setDeleteFlag(Long.valueOf(1));
                    insList.add(pro);
                } else if (list3.size() > 0 && list3.get(0).getDeleteFlag() == 1) {
                    pro.setId(list3.get(0).getId());
                    pro.setUpdateBy(userInfo.getUsername());
                    if (type == 2 || type == 3) {
                        pro.setScore(2);
                    } else if (type == 1) {
                        pro.setScore(1);
                    }
                    pro.setDeleteFlag(Long.valueOf(1));
                    updList.add(pro);
                }
            }
            String str = errorInfo.length() > 0 ? "导入的第" + errorInfo.toString() + "的位置题目没有填写;" : "";
            String str1 = errorInfo1.length() > 0 ? "导入的第" + errorInfo1.toString() + "的位置题型没有填写;" : "";
            String str2 = errorInfo2.length() > 0 ? "导入的第" + errorInfo2.toString() + "的位置选项A没有填写;" : "";
            String str3 = errorInfo3.length() > 0 ? "导入的第" + errorInfo3.toString() + "的位置选项B没有填写;" : "";
            String str4 = errorInfo4.length() > 0 ? "导入的第" + errorInfo4.toString() + "的位置选项C没有填写;" : "";
            String str5 = errorInfo5.length() > 0 ? "导入的第" + errorInfo5.toString() + "的位置选项D没有填写;" : "";
            String str6 = errorInfo6.length() > 0 ? "导入的第" + errorInfo6.toString() + "的位置答案没有填写;" : "";
            String str7 = errorInfo7.length() > 0 ? "导入的第" + errorInfo7.toString() + "的位置答案不在ABCD选项中;" : "";
            String str8 = errorInfo8.length() > 0 ? "导入的第" + errorInfo8.toString() + "的位置答案请在ABCD选项中且用逗号分隔;" : "";
            String str9 = errorInfo9.length() > 0 ? "导入的第" + errorInfo9.toString() + "的位置答案请为A或者B;" : "";
            if (hasError) {
                String test = ((str == null || "".equals(str)) ? "" : (str + "<br>")) + ((str1 == null || "".equals(str1)) ? "" : (str1 + "<br>")) + ((str2 == null || "".equals(str2)) ? "" : (str2 + "<br>")) + ((str3 == null || "".equals(str3)) ? "" : (str3 + "<br>")) + ((str4 == null || "".equals(str4)) ? "" : (str4 + "<br>")) +
                        ((str5 == null || "".equals(str5)) ? "" : (str5 + "<br>")) + ((str6 == null || "".equals(str6)) ? "" : (str6 + "<br>")) + ((str7 == null || "".equals(str7)) ? "" : (str7 + "<br>")) + ((str8 == null || "".equals(str8)) ? "" : (str8 + "<br>")) + ((str9 == null || "".equals(str9)) ? "" : (str9 + "<br>"));

                result.put("code", "-1");
                result.put("msg", test);
                return result;
            }
            if (insList.size() > 0) {
                insList.forEach(r -> {
                    this.insertDExamProblem(r);
                });
            }
            if (updList.size() > 0) {
                updList.forEach(r -> {
                    this.updateDExamProblem(r);
                });
            }

        } catch (Exception e) {
            result.put("code", "-1");
            result.put("msg", "系统异常");
        }
        result.put("code", "0");
        result.put("msg", "导入成功");
        return result;
    }
}
