package com.example.test_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {

    static final int DEFAULT_POSITION_ID = -1;
    static final int ITEMS_PER_PAGE = 12;

    @Autowired
    private WorkersService workersService;

    @RequestMapping("/")
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") Integer page){

        if (page < 0) page = 0;
        List<Worker> workers = workersService.findAll(PageRequest.of(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("positions", workersService.findPositions());
        model.addAttribute("workers", workers);
        model.addAttribute("allPages", getPageCount());

        return "index";
    }

    @RequestMapping("/worker_add_page")
    public String workerAddPage(Model model) {
        model.addAttribute("positions", workersService.findPositions());
        return "worker_add_page";
    }

    @RequestMapping("/position_add_page")
    public String groupAddPage() {
        return "position_add_page";
    }

    @RequestMapping("/position/{id}")
    public String listPosition(
            @PathVariable(value = "id") long positionId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Model model)
    {
        Position position = (positionId != DEFAULT_POSITION_ID) ? workersService.findPositions(positionId) : null;
        if (page < 0) page = 0;

        List<Worker> workers = workersService.findByPosition(position, PageRequest.of(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("positions", workersService.findPositions());
        model.addAttribute("workers", workers);
        model.addAttribute("byPositionsPages", getPageCount(position));
        model.addAttribute("positionId", positionId);

        return "index";
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("positions", workersService.findPositions());
        model.addAttribute("workers", workersService.findByPattern(pattern, null));

        return "index";
    }

    @RequestMapping(value = "/worker/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false)
                                               long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            workersService.deleteWorkers(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/worker/add", method = RequestMethod.POST)
    public String workerAdd(@RequestParam(value = "position") long positionId,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String email)
    {
        Position position = (positionId != DEFAULT_POSITION_ID) ? workersService.findPositions(positionId) : null;

        Worker worker = new Worker(position, name, surname, email);
        workersService.addWorker(worker);

        return "redirect:/";
    }
    @RequestMapping(value="/position/add", method = RequestMethod.POST)
    public String positionAdd(@RequestParam String name) {
        workersService.addPosition(new Position(name));
        return "redirect:/";
    }

    private long getPageCount() {
        long totalCount = workersService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCount(Position position) {
        long totalCount = workersService.countByPositions(position);
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
