package api.notebook;

import api.config.CustomUserDetails;
import api.config.users.UserService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotebookService {
    @Autowired
    private NotebookRepository notebookRepository;
    @Autowired
    UserService userService;

    public List<Notebook> getNotebook()  {
        return notebookRepository.findAll();
    }

    public Notebook getNotebookById( Long id) {
        Optional<Notebook> notebookOptional =  notebookRepository.findById(id);
        if(notebookOptional.isPresent()) {
            return notebookOptional.get();
        }
        return null;
    }

    public Notebook saveNotebook( Notebook note)
    {
        userService.getCurrentUSer().setNotebook(note);
        note.setProducer( userService.getCurrentUSer());
        return notebookRepository.save(note);
    }

}
