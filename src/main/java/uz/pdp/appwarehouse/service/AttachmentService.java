package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.AttachmentContent;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.AttachmentContentRepository;
import uz.pdp.appwarehouse.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Result uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = new Attachment();
        attachment.setName(file.getName());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);

        return new Result("File is saved.", true, savedAttachment.getId());
    }

    public Result downloadFile(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);

        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachment_Id(id);
            if (contentOptional.isPresent()) {
                AttachmentContent attachmentContent = contentOptional.get();
                // File nomini berish uchun xiamat qiladi
                response.setHeader("Content-Disposition", "attachment; filename = \"" + attachment.getName() + "\"");

                // File content-type ni berish uchun xizmat qiladi
                response.setContentType(attachment.getContentType());

                // File byte larini berish uchun xizmat qiladi
                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
                return new Result("File downloaded ...", true);

            }

        }
        return new Result("File not downloaded ...", false);
    }

    public Result deleteFile(Integer id) {

        Optional<Attachment> attachmentRepositoryById = attachmentRepository.findById(id);
        if (!attachmentRepositoryById.isPresent())
            return new Result("File not found ...", false);

        Attachment attachment = attachmentRepositoryById.get();
        Optional<AttachmentContent> attachmentContentRepositoryById = attachmentContentRepository.findById(attachment.getId());
        if (!attachmentContentRepositoryById.isPresent())
            return new Result("File not found ...", false);

        attachmentContentRepository.deleteById(attachment.getId());
        attachmentRepository.deleteById(id);
        return new Result("File deleted ...", false);
    }

    public Result reloadFile(MultipartHttpServletRequest request, Integer id) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Optional<Attachment> attachmentRepositoryById = attachmentRepository.findById(id);

        if (!attachmentRepositoryById.isPresent())
            return new Result("File not found ...", false);

        Attachment attachment = attachmentRepositoryById.get();
        Optional<AttachmentContent> attachmentContentRepositoryById = attachmentContentRepository.findById(attachment.getId());

        if (!attachmentContentRepositoryById.isPresent())
            return new Result("File not found ...", false);


        attachment.setName(file.getName());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);

        return new Result("File is saved.", true, savedAttachment.getId());
    }

}
