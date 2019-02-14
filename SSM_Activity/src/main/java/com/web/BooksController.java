package com.web;

import com.bean.Books;
import com.bean.Information;
import com.bean.Infotype;
import com.github.pagehelper.PageInfo;
import com.service.BooksService;
import com.service.InformationService;
import com.service.InfotypeService;
import com.util.BookExcelPoi;
import com.util.MenuExcelPoi;
import com.util.PageUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BooksController {
    @Autowired
    private BooksService service;
    @Autowired
    private InformationService informationService;
    @Autowired
    private InfotypeService infotypeService;
    //导出
    @RequestMapping("/daochubook")
    public void daoChuBook(int[] bid, HttpServletResponse response) {
        List<Books> list = service.findBooksByIds(bid);
        //导出数据
        BookExcelPoi.heads = new String[]{"编号","书籍名称","状态"};
        BookExcelPoi.createOne();
        BookExcelPoi.createOthers(list);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String s = simpleDateFormat.format(new Date());
        response.setContentType("application/x-download");
        String filename = s + ".xls";
        response.addHeader("Content-Disposition","attachement:filename=" + filename);
        try {
           BookExcelPoi.export(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //分页查询
    @RequestMapping("/book/getbooks")
    public String getBooks(ModelMap map,@RequestParam(value = "index",defaultValue = "1") int pageindex) {
        PageInfo pageInfo = service.getAll(pageindex, PageUtil.PAGESIZE);
        map.put("pageInfo",pageInfo);
        return "/book/list";
    }

    //资料上传展示数据
    @RequestMapping("/book/ziliaolist")
    public String ZiLiaoList(@RequestParam(value = "index",defaultValue = "1") int pageindex,ModelMap map,String title,
                             @RequestParam(value = "size" ,defaultValue = "5") int pagesize) {
        PageInfo pageInfo = informationService.getAllBooks(pageindex, pagesize, title);
        map.put("pageInfo",pageInfo);
        map.put("t",title);
        return "book/list-ziliao";
    }

    //模糊查询资料展示数据(上传)
    @RequestMapping("/book/mohuziliaolist")
    public String mohu(@RequestParam(value = "index",defaultValue = "1") int pageindex,ModelMap map,int tid,
                             @RequestParam(value = "size" ,defaultValue = "5") int pagesize) {
        PageInfo pageInfo = informationService.mohu(pageindex,pagesize,tid);
        map.put("pageInfo",pageInfo);
        map.put("t","");
        return "book/list-ziliao";
    }

    //查询资料类型
    @RequestMapping("/book/getfiletype")
    public String getfiletype(ModelMap map) {
        List<Infotype> list = informationService.getFileType();
        map.put("flist",list);
        return "/book/add";
    }

    //文件上传
    @RequestMapping("/book/addziliao")
    public String addZiLiao(Information information, MultipartFile myfile, HttpServletRequest request) {
        try {
            String realPath = request.getRealPath("/uploadfiles");
            String filename = myfile.getOriginalFilename();
            myfile.transferTo(new File(realPath + "/" + filename));
            String filetype = filename.substring(filename.indexOf(".") + 1);
            information.setFiletype(filetype);
            information.setUrl("/uploadfiles/" + filename);
            informationService.insertSelective(information);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/book/ziliaolist";
    }

    //文件下载(页面展示)
    @RequestMapping("/book/ziliaolistsecond")
    public String ZiLiaoList2(@RequestParam(value = "index",defaultValue = "1") int pageindex,ModelMap map,String title,
                             @RequestParam(value = "size" ,defaultValue = "5") int pagesize) {
        PageInfo pageInfo = informationService.getAllBooks(pageindex, pagesize, title);
        map.put("pageInfo",pageInfo);
        map.put("t",title);
        return "/study/StudentMaterial";
    }

    //模糊查询资料展示数据(下载)
    @RequestMapping("/book/mohusecond")
    public String mohuSecond(@RequestParam(value = "index",defaultValue = "1") int pageindex,ModelMap map,int tid,
                             @RequestParam(value = "size" ,defaultValue = "5") int pagesize) {
        PageInfo pageInfo = informationService.mohu(pageindex,pagesize,tid);
        map.put("pageInfo",pageInfo);
        map.put("t","");
        return "/study/StudentMaterial";
    }

    //文件下载(正式下载)
    @RequestMapping("/book/download")
    public ResponseEntity<byte[]> downloadFile(String file,HttpServletRequest request) {
        ResponseEntity<byte[]> resp= null;
        try {
            String serverpath = request.getRealPath(file);
            //创建头文件信息对象
            HttpHeaders headers = new HttpHeaders();
            //标记以流的方式响应信息
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置以弹窗的方式提示用户下载
            //attachment 表示以附件的形式响应给客户端
            String filename = file.substring(file.lastIndexOf("/") + 1);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(filename,"utf-8"));
            File f=new File(serverpath);
            resp = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f), headers, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    //资料详情
    @RequestMapping("book/xiangqing")
    public String xiangQing(int informationid,ModelMap map){
        Information ziLiao = informationService.getZiLiao(informationid);
        map.put("ziliao",ziLiao);
        return "/book/info-ziliao";
    }

    //资料编辑(页面展示)
    @RequestMapping("/book/edit")
    public String edit(int informationid,ModelMap map) {
        Information ziLiao = informationService.getZiLiao(informationid);
        List<Infotype> list = infotypeService.getAll();
        map.put("ziliao",ziLiao);
        map.put("list",list);
        return "/book/edit-ziliao";
    }

    //资料编辑(正式修改)
    @RequestMapping("/book/updateinformation")
    public String updateInformation(Information information) {
        informationService.updateByPrimaryKeySelective(information);
        return "redirect:/book/ziliaolist";
    }

    //禁用启用
    @RequestMapping("/book/updatebookstate")
    public String updateBookState(Books books) {
        if(books.getBookstate().equals("1")) {
            books.setBookstate("2");
        }else {
            books.setBookstate("1");
        }
        service.updateByPrimaryKeySelective(books);
        return "redirect:/book/getbooks";
    }
    //书籍详情
    @RequestMapping("/book/detail")
    public String bookDetail(int bookid,ModelMap map) {
        Books books = service.selectByPrimaryKey(bookid);
        map.put("books",books);
        return "/book/info";
    }

    //修改书籍(展示页面)
    @RequestMapping("/book/editbook")
    public String editBook(int bookid,ModelMap map) {
        Books books = service.selectByPrimaryKey(bookid);
        map.put("books",books);
        return "/book/edit";
    }
    //修改书籍(正式修改)
    @RequestMapping("/book/editbookbybid")
    public String editBookByBid(Books books) {
        service.updateByPrimaryKeySelective(books);
        return "redirect:/book/getbooks";
    }

    //新增书籍
    @RequestMapping("/book/addBook")
    public String addBook(Books books) {
        service.insertSelective(books);
        return "redirect:/book/getbooks";
    }

    //删除书籍
    @RequestMapping("/book/deletebook")
    public String deleteBook(int bookid) {
        service.deleteByPrimaryKey(bookid);
        return "redirect:/book/getbooks";
    }
}
