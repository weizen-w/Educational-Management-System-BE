package de.ait.ems.repositories;

import de.ait.ems.models.Attendance;
import de.ait.ems.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

  List<Attendance> getAttendanceByStudent(User student);
}