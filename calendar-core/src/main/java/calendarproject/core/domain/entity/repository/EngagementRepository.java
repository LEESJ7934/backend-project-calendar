package calendarproject.core.domain.entity.repository;
import calendarproject.core.domain.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
}
