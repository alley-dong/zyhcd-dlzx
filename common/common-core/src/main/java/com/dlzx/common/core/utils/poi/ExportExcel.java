package com.dlzx.common.core.utils.poi;

import com.dlzx.common.core.utils.domain.EveryDayDetailExcelVm;
import com.dlzx.common.core.utils.domain.PContractEvaluatExcel;
import com.dlzx.common.core.utils.domain.PContractEvaluateMetricsExcel;
import com.dlzx.common.core.utils.domain.VacationDayExcelVm;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExportExcel<T> {

    /**
     * @describe 合作方评价导出
     * @author DongCL 
     * @date 2021-02-24 17:25 
     * @param 
     * @return 
     */
    public void getValue(HttpServletResponse response, PContractEvaluatExcel pContractEvaluatExcel){
//        response.reset();
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");

//        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);

        // 技术部分
        List<PContractEvaluateMetricsExcel>  skillList = new ArrayList<>();
        // 商务部分
        List<PContractEvaluateMetricsExcel>  businessList = new ArrayList<>();
        for (PContractEvaluateMetricsExcel pContractEvaluateMetricsExcel : pContractEvaluatExcel.getpContractEvaluateMetricsList()) {
            if (pContractEvaluateMetricsExcel.getType()==1){
                skillList.add(pContractEvaluateMetricsExcel);
            }
            if (pContractEvaluateMetricsExcel.getType()==2){
                businessList.add(pContractEvaluateMetricsExcel);
            }
        }

        // 排序
        int total = skillList.size() + businessList.size();
        Collections.sort(skillList, new Comparator<PContractEvaluateMetricsExcel>(){
            @Override
            public int compare(PContractEvaluateMetricsExcel o1, PContractEvaluateMetricsExcel o2) {
               if (o1.getDimension().compareTo(o2.getDimension())>0){
                   return -1;
               }else if (o1.getDimension().compareTo(o2.getDimension())<0){
                   return 1;
               }else {
                   return 0;
               }
            }
        });
        // 排序
        Collections.sort(businessList, new Comparator<PContractEvaluateMetricsExcel>(){
            @Override
            public int compare(PContractEvaluateMetricsExcel o1, PContractEvaluateMetricsExcel o2) {
                if (o1.getDimension().compareTo(o2.getDimension())>0){
                    return -1;
                }else if (o1.getDimension().compareTo(o2.getDimension())<0){
                    return 1;
                }else {
                    return 0;
                }
            }
        });
//        int newSkillList = skillList.size();
//        int newBusinessList = businessList.size();

        try{
//            if(1==skillList.size()){
//                skillList.add(new PContractEvaluateMetricsExcel());
//                total = total + 1;
//            }
//            if(1==businessList.size()){
//                businessList.add(new PContractEvaluateMetricsExcel());
//                total = total + 1;
//            }
            //1.创建工作簿
            Workbook workbook = new SXSSFWorkbook(500);
            //1.1创建合并单元格对象
            CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,0,5);//起始行,结束行,起始列,结束列
            CellRangeAddress callRangeAddress1 = new CellRangeAddress(1,1,0,5);//起始行,结束行,起始列,结束列

            //标题
            CellRangeAddress callRangeAddress31 = new CellRangeAddress(2,2,0,1);//起始行,结束行,起始列,结束列
            CellRangeAddress callRangeAddress32 = new CellRangeAddress(2,2,2,2);//起始行,结束行,起始列,结束列
            CellRangeAddress callRangeAddress33 = new CellRangeAddress(2,2,3,3);//起始行,结束行,起始列,结束列
            CellRangeAddress callRangeAddress34 = new CellRangeAddress(2,2,4,4);//起始行,结束行,起始列,结束列
            CellRangeAddress callRangeAddress35 = new CellRangeAddress(2,2,5,5);//起始行,结束行,起始列,结束列

            //2.创建工作表
            Sheet sheet = workbook.createSheet("sheet");

            if (1!=skillList.size()){
                // 合并第一列（部分）
                CellRangeAddress callRangeAddress36 = new CellRangeAddress(3,skillList.size()-1+3,0,0);//起始行,结束行,起始列,结束列
                // update by 3.24 商业部分集合必须大于0
                if (businessList.size()>0){
                    CellRangeAddress callRangeAddress37 = new CellRangeAddress(3+skillList.size()+2,3+skillList.size()-1+2 + businessList.size(),0,0);//起始行,结束行,起始列,结束列
                    sheet.addMergedRegion(callRangeAddress37);
                }

                // 合并第二列 todo

                //技术合计
                CellRangeAddress callRangeAddressnumber1 = new CellRangeAddress(skillList.size()+3,skillList.size()+3,0,3);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressnumber2 = new CellRangeAddress(skillList.size()+3,skillList.size()+3,4,4);//起始行,结束行,起始列,结束列

                //技术权重得分
                CellRangeAddress callRangeAddressnumber3 = new CellRangeAddress(skillList.size()+4,skillList.size()+4,0,3);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressnumber4 = new CellRangeAddress(skillList.size()+4,skillList.size()+4,4,4);//起始行,结束行,起始列,结束列


                //商务合计
                CellRangeAddress callRangeAddressPersion1 = new CellRangeAddress(total+5,total+5,0,3);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressPersion2 = new CellRangeAddress(total+5,total+5,4,4);//起始行,结束行,起始列,结束列

                //商务权重得分
                CellRangeAddress callRangeAddressinfo = new CellRangeAddress(total+6,total+6,0,3);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo1 = new CellRangeAddress(total+6,total+6,4,4);//起始行,结束行,起始列,结束列

                // 部分
                CellRangeAddress callRangeAddressinfo2 = new CellRangeAddress(total+7,total+7,0,0);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo3= new CellRangeAddress(total+7,total+7,1,1);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo4= new CellRangeAddress(total+7,total+7,2,2);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo5= new CellRangeAddress(total+7,total+7,3,3);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo6= new CellRangeAddress(total+7,total+7,4,4);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo7= new CellRangeAddress(total+8,total+8,0,0);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo8= new CellRangeAddress(total+8,total+8,1,1);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo9= new CellRangeAddress(total+8,total+8,2,2);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo10= new CellRangeAddress(total+8,total+8,3,3);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo11= new CellRangeAddress(total+8,total+8,4,4);//起始行,结束行,起始列,结束列

                // 注
                CellRangeAddress callRangeAddressinfo12= new CellRangeAddress(total+9,total+9,0,0);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo13= new CellRangeAddress(total+10,total+10,0,2);//起始行,结束行,起始列,结束列
                CellRangeAddress callRangeAddressinfo14= new CellRangeAddress(total+11,total+11,0,2);//起始行,结束行,起始列,结束列

                sheet.addMergedRegion(callRangeAddress36);
                sheet.addMergedRegion(callRangeAddressnumber1);
                sheet.addMergedRegion(callRangeAddressnumber3);
                sheet.addMergedRegion(callRangeAddressPersion1);
                sheet.addMergedRegion(callRangeAddressinfo);
                sheet.addMergedRegion(callRangeAddressinfo13);
                sheet.addMergedRegion(callRangeAddressinfo14);
            }



            //部项目经理部
//            SXSSFCell headStyle = createCellStyle(workbook,(short)10,false,true);
            //派工单
//            XSSFCellStyle erStyle = createCellStyle(workbook,(short)13,true,true);
            //注
//            XSSFCellStyle sanStyle = createCellStyle(workbook,(short)10,false,false);
            //标题样式
//            XSSFCellStyle colStyle = createCellStyle(workbook,(short)10,true,true);
            //内容样式
//            XSSFCellStyle cellStyle = createCellStyle(workbook,(short)10,false,true);
            //2.创建工作表
//            Sheet sheet = workbook.createSheet("sheet");
            //2.1加载合并单元格对象
            sheet.addMergedRegion(callRangeAddress);
            sheet.addMergedRegion(callRangeAddress1);
            sheet.addMergedRegion(callRangeAddress31);
//            sheet.addMergedRegion(callRangeAddress36);
//            sheet.addMergedRegion(callRangeAddress37);
//            sheet.addMergedRegion(callRangeAddressnumber1);
//            sheet.addMergedRegion(callRangeAddressnumber3);
//            sheet.addMergedRegion(callRangeAddressPersion1);
//            sheet.addMergedRegion(callRangeAddressinfo);
//            sheet.addMergedRegion(callRangeAddressinfo13);
//            sheet.addMergedRegion(callRangeAddressinfo14);
            //设置默认列宽
//            sheet.setDefaultColumnWidth(15);
            //3.创建行
            //3.1创建头标题行;并且设置头标题
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            //加载单元格样式
//            cell.setCellStyle(headStyle);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
            String dateString = formatter.format(pContractEvaluatExcel.getEvaluateMonth());
            cell.setCellValue(dateString.substring(0,4)+"年"+ dateString.substring(5,6) +"月供应商评价表");

            Row rower = sheet.createRow(1);
            Cell celler = rower.createCell(0);
            //加载单元格样式
//            celler.setCellStyle(erStyle);
            celler.setCellValue("供应商名称："+pContractEvaluatExcel.getCarrierName());

            //3.2创建列标题;并且设置列标题
            Row row2 = sheet.createRow(2);
            String[] titles = {"维度","","评价内容","评价方法","得分"};//""为占位字符串
            for(int i=0;i<titles.length;i++)
            {
                Cell cell2 = row2.createCell(i);
                //加载单元格样式
//                cell2.setCellStyle(colStyle);
                cell2.setCellValue(titles[i]);
            }

            //4.操作单元格;将用户列表写入excel
            if(skillList.size()>0)
            {
//                if (newSkillList==1){
//                    skillList = skillList.stream().filter(s->s.getId()!= null).collect(Collectors.toList());
//                }
                int i=1;
                for(int j=0;j<skillList.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    Row row3 = sheet.createRow(j+3);
                    Cell cell0 = row3.createCell(0);
//                    cell0.setCellStyle(cellStyle);
                    cell0.setCellValue(skillList.get(j).getTypeEnum());

                    Cell cell1 = row3.createCell(1);
//                    cell1.setCellStyle(cellStyle);
                    cell1.setCellValue(skillList.get(j).getDimension());

                    Cell cell2 = row3.createCell(2);
//                    cell2.setCellStyle(cellStyle);
                    cell2.setCellValue(skillList.get(j).getContent()==null||"".equals(skillList.get(j).getContent())? "/":skillList.get(j).getContent());

                    Cell cell3 = row3.createCell(3);
//                    cell3.setCellStyle(cellStyle);
                    cell3.setCellValue(skillList.get(j).getMethod());

                    Cell cell4 = row3.createCell(4);
//                    cell4.setCellStyle(cellStyle);
                    cell4.setCellValue(skillList.get(j).getScore());

                }
            }


            Row rowStillTotal = sheet.createRow(skillList.size()+3);
            Cell cellStillTotal = rowStillTotal.createCell(0);
            Cell cellStillTotal1 = rowStillTotal.createCell(4);
            Row rowStillTotalMutl = sheet.createRow(skillList.size()+4);
            Cell cellStillTotal2 = rowStillTotalMutl.createCell(0);
            Cell cellStillTotal3 = rowStillTotalMutl.createCell(4);
            //加载单元格样式
//            cellStillTotal.setCellStyle(sanStyle);
            cellStillTotal.setCellValue("合计");
//            cellStillTotal1.setCellStyle(sanStyle);
            cellStillTotal1.setCellValue(pContractEvaluatExcel.getTotalScore());

            //加载单元格样式
//            cellStillTotal2.setCellStyle(sanStyle);
            cellStillTotal2.setCellValue("乘以权重70%得分");
//            cellStillTotal3.setCellStyle(sanStyle);
            cellStillTotal3.setCellValue(pContractEvaluatExcel.getWeightTotalScore()+"");


            //5.操作单元格;将用户列表写入excel
            if(businessList.size()>0)
            {
//                if (newBusinessList==1){
//                    businessList = businessList.stream().filter(s->s.getId()!= null).collect(Collectors.toList());
//                }
                int i=1;
                for(int j=0;j<businessList.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    Row row5 = sheet.createRow(j+5+skillList.size());
                    Cell cell0 = row5.createCell(0);
//                    cell0.setCellStyle(cellStyle);
                    cell0.setCellValue(businessList.get(j).getTypeEnum());

                    Cell cell1 = row5.createCell(1);
//                    cell1.setCellStyle(cellStyle);
                    cell1.setCellValue(businessList.get(j).getDimension());

                    Cell cell2 = row5.createCell(2);
//                    cell2.setCellStyle(cellStyle);
                    cell2.setCellValue(businessList.get(j).getContent()==null||"".equals(businessList.get(j).getContent())? "/":businessList.get(j).getContent());

                    Cell cell3 = row5.createCell(3);
//                    cell3.setCellStyle(cellStyle);
                    cell3.setCellValue(businessList.get(j).getMethod());

                    Cell cell4 = row5.createCell(4);
//                    cell4.setCellStyle(cellStyle);
//                    cell4.setCellValue(businessList.get(j).getScore());
                    cell4.setCellValue(" ");
                }
            }

            Row rowTotal = sheet.createRow(total+5);
            Cell cellBusinessTotal = rowTotal.createCell(0);
            Cell cellBusinessTotal1 = rowTotal.createCell(4);
            Row rowBusinessTotalMutl = sheet.createRow(total+6);
            Cell cellBusinessTotal2 = rowBusinessTotalMutl.createCell(0);
            Cell cellBusinessTotal3 = rowBusinessTotalMutl.createCell(4);

            Row rowBusinessAllMutl = sheet.createRow(total+7);
            Cell cellBusinessTotal4 = rowBusinessAllMutl.createCell(0);
            Cell cellBusinessTotal5 = rowBusinessAllMutl.createCell(4);
            //加载单元格样式
//            cellBusinessTotal.setCellStyle(sanStyle);
            cellBusinessTotal.setCellValue("合计");
//            cellBusinessTotal1.setCellStyle(sanStyle);
            cellBusinessTotal1.setCellValue("");

            //加载单元格样式
//            cellBusinessTotal2.setCellStyle(sanStyle);
            cellBusinessTotal2.setCellValue("乘以权重30%得分");
//            cellBusinessTotal3.setCellStyle(sanStyle);
            cellBusinessTotal3.setCellValue("");

            //加载单元格样式
//            cellBusinessTotal2.setCellStyle(sanStyle);
            cellBusinessTotal4.setCellValue("合计");
//            cellBusinessTotal3.setCellStyle(sanStyle);
            cellBusinessTotal5.setCellValue("");


            Row rownumber = sheet.createRow(total+8);
            Cell cellnumber = rownumber.createCell(0);
            Cell cellnumber1 = rownumber.createCell(1);
            Cell cellnumber2 = rownumber.createCell(2);
            Cell cellnumber3 = rownumber.createCell(3);
            Cell cellnumber4 = rownumber.createCell(4);
            //加载单元格样式

//            cellnumber.setCellStyle(sanStyle);
            cellnumber.setCellValue("技术部分");
//            cellnumber1.setCellStyle(sanStyle);
            if (pContractEvaluatExcel.getYearMoneyType()==1){
                cellnumber1.setCellValue("年度预算<200万元");
            }else {
                cellnumber1.setCellValue("年度预算>=200万元");
            }

//            cellnumber2.setCellStyle(sanStyle);
            cellnumber2.setCellValue("评价部门：（盖章）");
//            cellnumber3.setCellStyle(sanStyle);
            if (pContractEvaluatExcel.getYearMoneyType()==1){
                cellnumber3.setCellValue("评价人：                  审批：");
            }else {
                cellnumber3.setCellValue("评价人：                  审核：                  审批：");
            }
//            cellnumber4.setCellStyle(sanStyle);
            cellnumber4.setCellValue("");


            Row rownumbers = sheet.createRow(total+9);
            Cell cellnumber5 = rownumbers.createCell(0);
            Cell cellnumber6 = rownumbers.createCell(1);
            Cell cellnumber7 = rownumbers.createCell(2);
            Cell cellnumber8 = rownumbers.createCell(3);
            Cell cellnumber9 = rownumbers.createCell(4);
            //加载单元格样式
//            cellnumber5.setCellStyle(sanStyle);
            cellnumber5.setCellValue("商务部分");
//            cellnumber6.setCellStyle(sanStyle);
            cellnumber6.setCellValue("");
//            cellnumber7.setCellStyle(sanStyle);
            cellnumber7.setCellValue("评价部门：（盖章）");
//            cellnumber8.setCellStyle(sanStyle);
            cellnumber8.setCellValue("评价人：");
//            cellnumber9.setCellStyle(sanStyle);
            cellnumber9.setCellValue("");

            Row rowinfo = sheet.createRow(total+10);
            Cell cellinfo = rowinfo.createCell(0);
//            cellinfo.setCellStyle(sanStyle);
            cellinfo.setCellValue("注：");

            Row rowinfo1 = sheet.createRow(total+11);
            Cell cellinfo1 = rowinfo1.createCell(0);
            Cell cellinfo2 = rowinfo1.createCell(2);
//            cellinfo1.setCellStyle(sanStyle);
            cellinfo1.setCellValue("1、评价满分为100分，将技术部分得分和商务部分得分分别乘以权重后，相加即为该供应商最终得分；");
            cellinfo2.setCellValue("");

            Row rowinfo2 = sheet.createRow(total+12);
            Cell cellinfo3 = rowinfo2.createCell(0);
            Cell cellinfo4 = rowinfo2.createCell(2);
//            cellinfo3.setCellStyle(sanStyle);
            cellinfo3.setCellValue("2、得分>=90分为优秀，80分<=得分<90分为及格，低于80分为不及格");
            cellinfo4.setCellValue("");

            response.reset(); // 清除buffer缓存
            try {
                String fileName = "供应商评价.xls";
                response.setHeader("Content-Disposition", "attachment;filename="+  URLEncoder.encode(fileName, "UTF-8"));//设置文件名
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            OutputStream output;
            try {
                output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                bufferedOutPut.flush();
                workbook.write(bufferedOutPut);
                bufferedOutPut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //5.输出
//            workbook.write(response.getOutputStream());
//            workbook.close();
            //out.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param workbook
     * @param fontsize
     * @return 单元格样式
     */
    private static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, short fontsize,boolean flag,boolean flag1) {
        // TODO Auto-generated method stub
        XSSFCellStyle style = workbook.createCellStyle();
        //是否水平居中
        if(flag1){
            style.setAlignment(HorizontalAlignment.CENTER);//水平居中
        }

        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //创建字体
        XSSFFont font = workbook.createFont();
        //是否加粗字体
        if(flag){
//            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        }
        font.setFontHeightInPoints(fontsize);
        //加载字体
        style.setFont(font);
        return style;
    }


    /**
     * @describe 考勤统计导出
     * @author DongCL
     * @date 2021-02-24 17:27
     * @param
     * @return
     */
    public void VacationDayExcel(HttpServletResponse response, List<VacationDayExcelVm> vacationDayExcelVms){

        if (vacationDayExcelVms != null && vacationDayExcelVms.size()>0){
            try {
                // 根据日期排序
                vacationDayExcelVms.stream().map(vacationDayExcelVm -> {
                    Collections.sort(vacationDayExcelVm.getdVacationDayList(), new Comparator<EveryDayDetailExcelVm>(){
                        @Override
                        public int compare(EveryDayDetailExcelVm o1, EveryDayDetailExcelVm o2) {
                            if (o1.getDay().compareTo(o2.getDay())<0){
                                return -1;
                            }else if (o1.getDay().compareTo(o2.getDay())>0){
                                return 1;
                            }else {
                                return 0;
                            }
                        }
                    });
                    return vacationDayExcelVm;
                }).collect(Collectors.toList());


                //1.创建工作簿
                Workbook workbook = new SXSSFWorkbook(500);

                // 第一行标题
                List<String> dateList = new ArrayList<>();
                // 第一行数据
                dateList = addDateOne();

                //2.创建工作表
                Sheet sheet = workbook.createSheet("sheet");
                Row row = sheet.createRow(0);
                for (int i = 0; i < dateList.size(); i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(dateList.get(i));
                }

                // 驾驶员考勤数据
                for (int i = 0; i < vacationDayExcelVms.size(); i++) {
                    // 遍历行
                    Row row2 = sheet.createRow(i+1);
                    // 遍历列值
                    Cell cell1 = row2.createCell(0);
                    cell1.setCellValue(i+1);
                    Cell cell2 = row2.createCell(1);
                    cell2.setCellValue(vacationDayExcelVms.get(i).getDriverName());

                    // 考勤详情
                    for (int i1 = 0; i1 < vacationDayExcelVms.get(i).getdVacationDayList().size(); i1++) {
                        Cell cell3 = row2.createCell(vacationDayExcelVms.get(i).getdVacationDayList().get(i1).getDay()+1);
                        cell3.setCellValue(vacationDayExcelVms.get(i).getdVacationDayList().get(i1).getDetail());
                    }

                    // 在途
                    Cell cell4 = row2.createCell(dateList.size()-4);
                    cell4.setCellValue(vacationDayExcelVms.get(i).getWay());

                    // 在家
                    Cell cell5 = row2.createCell(dateList.size()-3);
                    cell5.setCellValue(vacationDayExcelVms.get(i).getAtHome());

                    // 请假
                    Cell cell6 = row2.createCell(dateList.size()-2);
                    cell6.setCellValue(vacationDayExcelVms.get(i).getLeave());

                    // 实际出勤
                    Cell cell7 = row2.createCell(dateList.size()-1);
                    cell7.setCellValue(vacationDayExcelVms.get(i).getTotalAttendance());
                }

                response.reset(); // 清除buffer缓存
                try {
                    String fileName = "考勤统计.xls";
                    response.setHeader("Content-Disposition", "attachment;filename="+  URLEncoder.encode(fileName, "UTF-8"));//设置文件名
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);

                OutputStream output;
                try {
                    output = response.getOutputStream();
                    BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                    bufferedOutPut.flush();
                    workbook.write(bufferedOutPut);
                    bufferedOutPut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // 第一行数据
    private List<String> addDateOne() {
        List<String> list = new ArrayList<>();
        list.add("序号");
        list.add("司机名称");
        for (int i = 1; i < 32; i++) {
            list.add(String.valueOf(i));
        }
        list.add("在途");
        list.add("在家");
        list.add("请假");
        list.add("实际出勤");
        return list;
    }
}
