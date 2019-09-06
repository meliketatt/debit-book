package api.notebook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/debit/notebook")
public class NotebookController {
    @Autowired
    private NotebookService notebookService;


    @GetMapping("/get")
    public List<Notebook> getNotebook()  {
        return notebookService.getNotebook();
    }

    @GetMapping("/get-by-id/{id}")
    public Notebook getNotebookById(@PathVariable Long id) {
        return notebookService.getNotebookById(id);
    }

    @PostMapping("/save")
    public Notebook saveNotebook(@RequestBody Notebook note)throws ParseException {
        return notebookService.saveNotebook(note);
    }

}
