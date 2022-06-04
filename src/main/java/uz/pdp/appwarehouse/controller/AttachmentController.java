package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/add/upload")
    public Result upload(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.uploadFile(request);
    }

    @GetMapping("/get/download")
    public Result download(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        return attachmentService.downloadFile(id, response);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteFile(@PathVariable Integer id) {
        return attachmentService.deleteFile(id);
    }

    @PutMapping("/edit/reload/{id}")
    public Result reloadFile(HttpServletResponse response, @PathVariable Integer id) throws IOException {
        return attachmentService.reloadFile((MultipartHttpServletRequest) response, id);
    }

}
