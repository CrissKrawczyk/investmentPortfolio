package pl.portfolio.domain.controller.dto.group;

import java.util.List;

public record GroupResponse(String name, List<AccountInGroupResponse> accounts) {
}
