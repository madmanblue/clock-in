package com.madman.future;

import com.github.liuhuagui.gridexcel.GridExcel;
import com.github.liuhuagui.gridexcel.util.ExcelType;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class ExcelUtilTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private void appendSql(String sql) {
        try {
            File file = new File("import.sql");
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append(sql);
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExcelUtil() throws Exception {

        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("dh_smt_map.xlsx");
        GridExcel.readByEventModel(resourceAsStream, Map.class, ExcelType.XLSX)
                .window(10, ts -> {
                    if (null != ts && !ts.isEmpty()){
                        for (Map map : ts) {
                            if (null != map){
                                String smt_cate_id = (String) map.get("smt_cate_id");
                                String dh_cate_id = (String) map.get("dh_cate_id");
                                appendSql(String.format("insert into prod_import_cate_map(ae_cate_id, dh_cate_id) VALUES (%s, %s);", smt_cate_id, dh_cate_id));
                            }

                        }
                    }

                })
                .process(cs -> {
                    if (cs.size()< 4){
                        System.out.println(String.format("当前id未匹配到：%s", cs.get(0)));
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("smt_cate_id", cs.get(0));
                        map.put("dh_cate_id", cs.get(2));
                        return map;
                    }

                    return null;
                }, 1);
    }

    @Test
    public void testFile() throws FileNotFoundException {
        for (int i = 0; i < 10; i++) {
            appendSql("sql" +i);
        }
    }

    @Test
    public void matchCategory() throws Exception {
        InputStream test = Thread.currentThread().getContextClassLoader().getResourceAsStream("dh_smt_map.xlsx");
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ali0318.xlsx");
        GridExcel.readByEventModel(resourceAsStream, Map.class, ExcelType.XLSX)
                .window(10, ts -> {
                    if (null != ts && !ts.isEmpty()){
                        for (Map map : ts) {
                            if (null != map && !map.isEmpty()){
                                String ae_cate_name = (String) map.get("ae_cate_name");
                                String dh_cate_name = (String) map.get("dh_cate_name");
                                String dh_category_id = (String) map.get("dh_category_id");
                                String ae_category_id = (String) map.get("ae_category_id");
                                Pattern pattern = Pattern.compile("[^0-9]");
                                Matcher matcher = pattern.matcher(ae_category_id);
                                String ae = matcher.replaceAll("");
                                String sql = "insert into item_category (source_display_id, source_display_name, dh_cate_pub_id, dh_cate_pub_name, leaf, valid, create_date) values (?,?,?,?,?,?,?)";
                                jdbcTemplate.update(sql, ae, ae_cate_name, dh_category_id, dh_cate_name, 1, 1, new Date());
                                System.out.println(String.format("--> %s , %s, %s, %s", ae_cate_name, ae, dh_cate_name, dh_category_id));

                            }

                        }
                    }

                })
                .process(cs -> {
                    if (cs.size() == 4){
                        Map<String, Object> map = new HashMap<>();
                        map.put("ae_cate_name", cs.get(0));
                        map.put("dh_cate_name", cs.get(1));
                        map.put("dh_category_id", cs.get(2));
                        map.put("ae_category_id", cs.get(3));
                        return map;
                    } else if (cs.size() > 0){
                        Map<String, Object> map = new HashMap<>();
                        map.put("ae_cate_name", cs.get(0));
//                        map.put("dh_cate_name", cs.get(1));
//                        map.put("dh_category_id", cs.get(3));
                        map.put("ae_category_id", cs.get(1));
                        return map;
                    } else {
                        return new HashMap<String, Object>();
                    }

                }, 1);
    }



    @Test
    public void testali() throws Exception {
        InputStream test = Thread.currentThread().getContextClassLoader().getResourceAsStream("dh_smt_map.xlsx");
        InputStream ali = Thread.currentThread().getContextClassLoader().getResourceAsStream("ali0318.xlsx");
        GridExcel.readByEventModel(ali, Map.class, ExcelType.XLSX)
                .window(10, ts -> {
                    if (null != ts && !ts.isEmpty()){
                        for (Map map : ts) {
                            if (null != map){

                            }

                        }
                    }

                })
                .process(cs -> {
                    System.out.println(cs.size());
                    HashMap map = new HashMap();
                    map.put("alcate",cs.get(0));
                    return map;

                }, 1);
    }


}
