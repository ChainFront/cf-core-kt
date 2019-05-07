/*
 * Copyright (c) 2019 ChainFront LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pcrypto.cf.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode( callSuper = true )
@Table( name = "api_user" )
@Entity
public class ApiUserDomain
      extends AbstractAuditableDomain
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
                     generator = "api_user_id_gen" )
    @SequenceGenerator( name = "api_user_id_gen",
                        sequenceName = "api_user_seq",
                        allocationSize = 1 )
    private Long id;

    @NotBlank( message = "User name is a required field" )
    private String userName;

    @NotBlank( message = "First name is a required field" )
    private String firstName;

    @NotBlank( message = "Last name is a required field" )
    private String lastName;

    private String email;

    private String phone;

    private Boolean locked;

    private Boolean expired;

    @Null
    private LocalDateTime deletedDate;


    @NotBlank( message = "Password required" )
    private String password;

    //    /* This field is transient because it's only used during regsistration. */
    //    private transient String confirmPassword;
    //
    //    // @Pattern(regexp = "^\\+?[0-9 ()-]{10,14}$", message =
    //    // "Format: ###-###-####")
    //    private String phoneNumber;

    //    private Set<String> userRoles = new HashSet<>();

    //    private Set<String> adminRoles = new HashSet<>();



    //    public Set<String> getRoles()
    //    {
    //        final Set<String> allRoles = new HashSet<>();
    //        allRoles.addAll( adminRoles );
    //        allRoles.addAll( userRoles );
    //        return allRoles;
    //    }
}
