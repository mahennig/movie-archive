package de.hennig.moviearchive.services;

import com.google.common.collect.Lists;
import de.hennig.moviearchive.domain.Folder;
import de.hennig.moviearchive.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping
public class TestDataBuilder {

    @Autowired
    FolderRepository folderRepo;

    @RequestMapping(value = "/1", method = RequestMethod.GET, produces = {"application/json"})
    @Transactional
    public @ResponseBody
    String persistFolder() {
        Folder testFolder = new Folder();
        testFolder.setFolderNumber(1);
        folderRepo.save(testFolder);
        return "Bla";
    }

    @RequestMapping(value = "/2", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody
    List<Folder> sizeFolder() {
        return Lists.newArrayList(folderRepo.findAll());
    }


}
