package uz.lee.news_app.archived;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.aop.CheckPermissions;
import uz.lee.news_app.custom_responses.ApiResponse;

import java.security.Principal;

@RestController
@RequestMapping("/api/archived")
public class ArchivedController {
    private final ArchiveService service;

    public ArchivedController(ArchiveService service) {
        this.service = service;
    }

    @CheckPermissions(permission = "CAN_ARCHIVE_POST")
    @PostMapping("/{username}/archive")
    public ResponseEntity<?> archive(@RequestBody ArchivedDto dto, @PathVariable String username,Principal principal) {
        service.checkPermission(username,principal.getName());
        return ResponseEntity.ok(service.archive(dto));
    }

    @CheckPermissions(permission = "CAN_GET_ARCHIVES")
    @GetMapping("/{username}/archives")
    public ResponseEntity<?> getAll(@PathVariable String username,Principal principal) {
        service.checkPermission(username,principal.getName());
        return ResponseEntity.ok(service.getUserArchives(username));
    }

    @CheckPermissions(permission = "CAN_REMOVE_ARCHIVE")
    @DeleteMapping("/{username}/archives/{archivedId}")
    public ResponseEntity<?> removeFromArchives(@PathVariable Long archivedId, @PathVariable String username, Principal principal){
        service.checkPermission(username,principal.getName());
        return ResponseEntity.ok(service.remove(archivedId));
    }

}
