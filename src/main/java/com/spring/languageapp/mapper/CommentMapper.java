//package com.spring.languageapp.mapper;
//
//import com.spring.languageapp.dto.CommentRequestDTO;
//import com.spring.languageapp.dto.CommentResponseDTO;
//import com.spring.languageapp.model.Comment;
//import org.springframework.web.bind.annotation.Mapping;
//
//@Mapper(componentModel = "spring") //pt a folosi metodele de mapare
//public abstract class CommentMapper {
//    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")//atributul... din ...
//    public abstract Comment mapDtoToSubreddit (CommentRequestDTO literaryWorkRequestDTO);
//
//    @Mapping(target = "  ", expression = "java(literaryWorkPost.getliteraryWorkPostList().size())")//target-ul e mereu ce iese, ce vreau sa construiesc; la expression->ce met vreau sa apelez care sa imi care sa imi calculeze noOfPosts
//    public abstract CommentResponseDTO mapCommentResponeDTO (Comment literaryWorkPost);
//}


//<plugin>
//				<groupId>org.apache.maven.plugins</groupId>
//				<artifactId>maven-compiler-plugin</artifactId>
//				<version>3.5.1</version>
//				<configuration>
//					<source>16</source>
//					<target>16</target>
//					<annotationProcessorPaths>
//						<path>
//							<groupId>org.mapstruct</groupId>
//							<artifactId>mapstruct-processor</artifactId>
//							<version>1.4.2.Final</version>
//						</path>
//					</annotationProcessorPaths>
//				</configuration>
//			</plugin>