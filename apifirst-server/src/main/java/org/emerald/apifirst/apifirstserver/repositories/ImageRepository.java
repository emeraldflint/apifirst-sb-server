package org.emerald.apifirst.apifirstserver.repositories;

import org.emerald.apifirst.apifirstserver.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}