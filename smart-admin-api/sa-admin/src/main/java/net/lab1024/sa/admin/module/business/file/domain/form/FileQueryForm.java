package net.lab1024.sa.admin.module.business.file.domain.form;

import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;

import java.time.LocalDate;


@Data
public class FileQueryForm extends PageParam {

    private Integer folderType;

    private String fileName;

    private String fileKey;

    private String fileType;

    private String creatorName;

    private LocalDate createTimeBegin;

    private LocalDate createTimeEnd;

}
